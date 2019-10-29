## Okhttp源码剖析

说明：请大家自行查看源码加之我的解释进行独立思考。

### 关联知识点：数据结构-数组/Deque队列(用于缓存)，Java基础-并发/队列集合，Android基础与技术-数据缓存，设计模式-单例。

**1.入口-通过建造者模式构建Request对象**

**2.OkHttpClient#newCall：**

实际调用RealCall#newRealCall，最后返回RealCall即Call的实现类。

call#execute/enqueue：

实际调用RealCall#execute/enqueue。

execute：同步操作，请求网络调用getResponseWithInterceptorChain返回数据。

enqueue：异步操作，加同步锁，最终请求由dispatcher来完成，具体操作由内部类AsynCall来完成。

**3.dispatcher#enqueue：**

Dispatcher：任务调度，负责控制并发请求

属性：maxRequests-最大并发请求数为64，maxRequestsPerHost-每个主机的最大请求数为5，executorService-消费者线程池，readyAsyncCalls-将要运行的异步请求队列，runningAsyncCalls-正在运行的异步请求队列，runningSyncCalls-正在运行的同步请求队列。

- 说明：消费者线程池ExecutorService：类似于 CachedThreadPool，比较适合执行大量的耗时比较少的任务。

构造方法：可以自己设定线程池；没有设定的话，请求网络前自己创建默认线程池。

enqueue方法：

- 加synchronized同步锁：表明同一时刻只能有一个请求在进行。
- call 加入到将要运行的异步请求队列 readyAsyncCalls 中进行缓存等待，随后加载到正在运行的异步请求队列runningAsyncCalls中。
- enqueue方法有一个构造参数：AsyncCall，是RealCall的内部类，里边有execute方法。无论请求结果如何，都会执行client.dispatcher().finished(this)即将此次请求从runningAsyncCalls移除后再从readyAsyncCalls取出下一个请求， 加入runningAsyncCalls中执行；execute方法中通过getResponseWithInterceptorChain方法请求网络，返回Response。

**4.getResponseWithInterceptorChain请求网络过程中会有拦截器Interceptor**

创建拦截器链，然后拦截器链调用proceed方法，从拦截器列表中取出拦截器，存在多个拦截器时阻塞， 并等待下一个拦截器调用返回。代码：Response response = interceptor.intercept(next)。如果没有更多拦截器，就执行网络请求。CallServerInterceptor类intercept里有网络请求的过程，通过HttpMethod来完成。

拦截器：一种能够监控、重写、重试调用的机制。 通常情况下， 拦截器用来添加、 移除、 转换请求和响应的头部信息。 比如将域名替换为IP地址， 在请求头中添加host属性； 也可以添加应用中的一些公共参数， 比如设备id、版本号等。
![image](https://github.com/2211785113/Blog/blob/master/images/okhttp_in.jpg)

**5.缓存策略**

发送请求，做缓存操作。CacheInterceptor类中。cacheCandidate：上次与服务器交互时缓存的Response，这里的缓存均基于Map；key是请求中url 的 md5，value 是在文件中查询到的缓存，页面置换基于 LRU 算法。cacheCandidate是一个可以读取缓存Header的Response。根据cacheStrategy的处理得到了networkRequest和cacheResponse这两个值，根据这两个值的数据是否为null来进行进一步的处理。在networkRequest和cacheResponse都为null的情况下， 即不进行网络请求且缓存不存在或过期，返回504错误；当networkRequest为null即不进行网络请求，如果缓存可用则直接返回缓存， 其他情况请求网络。

解析HTTP响应报头：如果有缓存且可用，则用缓存数据并更新缓存，否则用网络请求返回的数据。判断缓存是否可用：如果缓存有效，则返回304 Not Modified， 否则直接返回body；如果缓存过期或者强制放弃缓存，则缓存策略全部交给服务器判断， 客户端只需要发送条件 GET 请求即可。

条件GET请求有两种方式：一种是Last-Modified-Date，另一种是 ETag。这里采用了Last-Modified-Date， 通过缓存和网络请求响应中的LastModified计算是否是最新数据。 如果是，则缓存有效。

**6.失败重连**

RealCall#getResponse：当发生 IOException 或 RouteException 时都执行HttpEngine#recover：最后一行，重新创建 HttpEngine 并返回，用来完成重连。

### 原理

大概流程：new Call返回RealCall，RealCall请求网络最终由Dispatcher来控制请求并发。call开始执行，请求加载到正在运行的请求队列中并在线程池中执行，其余的放在将要运行的请求队列中缓存等待。Interceptor拦截器。没有拦截器就请求网络。缓存策略。失败重连。

流程图：

![image](https://github.com/2211785113/Blog/blob/master/images/okhttp.jpg)

**复用连接池：**

类：ConnectionPool->RealConnectionPool。

属性：

- maxIdleConnections-空闲的socket最大连接数
- keepAliveDurationNs-socket的keep-alive时间
- Executor-线程池，类似于CachedThreadPool，适合执行大量且耗时比较少的任务。线程池工作队列SynchronousQueue，没有容量。
- Deque-双端队列同时具有队列和栈性质， 经常在缓存中被使用， 里面维护了RealConnection也就是socket物理连接的包装。
- RouteDatabase-记录连接失败的路线名单， 连接失败时就把失败的线路加进去。

构造方法：默认空闲socket最大连接数为5个(支持5个并发socket连接)，socket的默认keepAlive时间为5分钟。

初始化：源码OkHttpClient.Builder().connectionPool

缓存操作：ConnectionPool对Deque＜RealConnection＞(用作缓存，存储连接)进行缓存操作：put、get、connectionBecameIdle、evictAll。放入、获取、移除、移除所有连接。

- put：添加到Deque之前首先要清理空闲线程。
- get：遍历connections缓存列表。 当某个连接计数的次数小于限制的大小， 并且request的地址和缓存列表中此连接的地址完全匹配时， 则直接复用缓存列表中的connection作为request的连接。
- 自动回收连接：put操作之前会调用executor.execute(cleanupRunnable)来清理闲置线程。cleanupRunnable是开启了一个子线程，不断地调用cleanup方法进行清理，并返回下次需要清理的间隔时间，然后调用wait方法进行等待以释放锁与时间片；当等待时间到了后，再次进行清理，并返回下次要清理的间隔时间；如此循环下去。
- 自动回收连接：cleanup清理工作：inUseConnectionCount为活跃连接数，idleConnectionCount为闲置连接数，longestIdleConnection为长期空闲连接，longestIdleDurationNs为长期空闲间隔时间；pruneAndGetAllocationCount中根据连接中的引用计数来判断连接是否闲置，为0则是空闲连接，否则就是活跃连接；如果空闲连接keepAlive时间超过5分钟，或空闲连接数超过5个，则从Deque中移除此连接；接下来根据空闲连接或者活跃连接来返回下次需要清理的时间数：如果空闲连接大于 0，则返回此连接即将到期的时间；如果都是活跃连接并且大于0，则返回默认的keepAlive时间5分钟；如果没有任何连接，跳出循环并返回-1。
- 自动回收连接：判断连接是否闲置pruneAndGetAllocationCount。遍历传进来的RealConnection的StreamAllocation列表：如果StreamAllocation被使用，接着遍历下一个StreamAllocation； 如果StreamAllocation未被使用，从列表中移除。 最后如果列表为空，说明此连接没有引用了，这时返回 0，表示此连接是空闲连接； 否则就是活跃连接。
- 引用计数：使用引用计数的方式跟踪socket流的调用。计数对象是StreamAllocation，被反复执行acquire与release操作在改变RealConnection中的List<Reference<StreamAllocation>>的大小。RealConnection是socket物理连接的包装，里面维护了List<Reference<StreamAllocation>>的引用；List中StreamAllocation的数量也就是socket被引用的计数：如果计数为0，说明此连接没有被使用，即空闲的，需要通过下文的算法实现回收；如果计数不为0，说明上层代码仍然在引用，无须关闭连接。

总流程：Deque用作缓存，存储连接；用四个方法进行缓存操作；通过判断连接中的计数对象StreamAllocation来自动回收连接。








