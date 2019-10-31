## Volley源码剖析

说明：请大家自行查看源码加之我的解释进行独立思考。

关联知识点：数据结构-数组，Java基础-并发/队列集合，Android基础与技术-数据缓存，设计模式-单例。

### Volley简介(加深大家理解)

我们平时在开发Android应用的时候不可避免地都需要用到网络技术，而多数情况下应用程序都会使用HTTP协议来发送和接收网络数据。Android系统中主要提供了两种方式来进行HTTP通信，HttpURLConnection和HttpClient，几乎在任何项目的代码中我们都能看到这两个类的身影，使用率非常高。

不过HttpURLConnection和HttpClient的用法还是稍微有些复杂的，如果不进行适当封装的话，很容易就会写出不少重复代码。于是乎，一些Android网络通信框架也就应运而生，比如说AsyncHttpClient，它把HTTP所有的通信细节全部封装在了内部，我们只需要简单调用几行代码就可以完成通信操作了。再比如Universal-Image-Loader，它使得在界面上显示网络图片的操作变得极度简单，开发者不用关心如何从网络上获取图片，也不用关心开启线程、回收图片资源等细节，Universal-Image-Loader已经把一切都做好了。

Android开发团队也是意识到了有必要将HTTP的通信操作再进行简单化，于是在2013年Google I/O大会上推出了一个新的网络通信框架——Volley。Volley可是说是把AsyncHttpClient和Universal-Image-Loader的优点集于了一身，既可以像AsyncHttpClient一样非常简单地进行HTTP通信，也可以像Universal-Image-Loader一样轻松加载网络上的图片。除了简单易用之外，Volley在性能方面也进行了大幅度的调整，它的设计目标就是非常适合去进行数据量不大，但通信频繁的网络操作，而对于大数据量的网络操作，比如说下载文件等，Volley的表现就会非常糟糕。

### 源码分析

#### 1.入口-创建请求队列：Volley#newRequestQueue

newRequestQueue方法有4个构造函数，下列依次执行：

- 参数为Context：调用下边构造函数。
- 参数为Context和BaseHttpStack：Android 2.3版本以上用HttpURLConnection的HurlStack；2.2版本及以下用HttpClient的HttpClientStack。
- 参数为Context和Network：创建请求队列RequestQueue，请求队列里放了4个网络调度线程NetworkDispatcher和1个缓存调度线程CacheDispatcher，分别start启动，所以有5个线程在后台运行并等待请求的到来。

#### 2.创建各种Request，并add到请求队列：RequestQueue#add

- 添加请求时，有一个同步块，同一时刻只能有一个请求在执行。
- 如果不可以缓存，直接添加请求到网络调度线程；如果可以缓存，判断之前是否有执行相同的请求且还没有返回结果，如果有，将请求加入mWaitingRequests等待队列，不再重复请求；如果没有，将请求加入缓存队列。
- add方法没有请求网络或对缓存进行操作。当将请求添加到网络请求队列或缓存队列时，在后台的网络调度线程和缓存调度线程轮询各自的请求队列，有请求任务则开始执行。请往下看。

#### 3.缓存调度线程CacheDispatcher#run：

- 先将线程设置为后台线程；
- while(true)循环处理请求，注意这里如果线程中断会抛出一个异常，isInterrupted由true置为false，再调用Thread.currentThread().interrupt()来中断线程。
- 处理请求processRequest，从缓存队列mCacheQueue中取出一个请求，如果请求被取消，就结束请求；然后获取缓存的响应，如果缓存为空，或者过期，并且不在等待队列中时，把请求加入到网络调度线程。如果有缓存响应且没有过期，对数据进行解析并回调给主线程。

#### 4.网络调度线程NetworkDispatcher#run：

- 前两步同上
- 处理请求processRequest，从网络队列mQueue中取出一个请求，如果请求被取消，就结束请求；然后请求网络得到请求数据，如果请求可以缓存并且有缓存响应，将请求加入缓存中，最后对数据进行解析并回调给主线程。
- mNetwork.performRequest(request)：实为BasicNetwork#performRequest，调用HttpStack#performRequest请求网络，根据不同的响应状态码返回不同的NetworkResponse。HttpStack也是一个接口，实现它的两个类分别是前面的HurlStack和HttpClientStack。
- mDelivery.postResponse：调用接口ResponseDelivery中postResponse方法，ctrl+o搜索类，得到ResponseDeliveryRunnable类，实现了Runnable接口在子线程中发送响应内容给主线程。StringRequest#deliverResponse：最后通过接口回调把数据回调给了new StringRequest。

### 原理

Volley请求是基于请求队列的。首先创建请求队列，并启动，里边有4个网络调度线程和1个缓存调度线程，在后台等待请求到来。然后请求队列添加请求，默认可以缓存，如果之前请求过，放等待队列里，没有请求过放缓存队列里。不可以缓存添加到网络队列。在缓存调度线程中，如果有缓存响应且没有过期，对缓存响应并回调给主线程；如果没有缓存响应，加到网络调度线程。在网络调度线程中，请求网络得到响应回调给主线程，需要的话可以把结果存入缓存。

本质：请求获取顺序————先缓存后网络，缓存没有就去网络获取。

![image](https://github.com/2211785113/Blog/blob/master/images/volley.jpg)

Volley分为三类线程，分别是主线程、缓存调度线程和4个网络调度线程。首先请求会加入缓存队列，缓存调度线程从缓存队列中取出请求。如果找到该请求的缓存响应就直接读取缓存的响应并解析，然后回调给主线程；如果没有找到缓存的响应，则将这条请求加入网络队列，然后网络调度线程会轮询取出网络队列中的请求，取出后发送HTTP请求，解析响应并将响应存入缓存，并回调给主线程。

### Volley拷问

Volley：可扩展性强，适合数据量小且频繁的请求。

1.并发实现：网络调度线程和缓存调度线程通过while循环不断从请求队列中获取请求，实现并发请求。

2.while循环不会影响性能：有界无序阻塞队列，队列为空或满时线程都会挂起。有请求才会继续运行。

3.优先级：每个请求按优先级放入队列，取出队列头部优先级最高。

4.没有用线程池：4个网络调度线程其实也等同于线程池的作用，避免了新建线程和回收线程的开销，但是缺点是短时间内无法对很多请求作出回应。线程池的好处：会根据请求数量，动态增加或减少线程数。

5.有些request放到等待请求Map里的作用：避免相同的URL进行重复请求，避免浪费资源；如果可以缓存，判断网络是否请求过，如果正在请求就放到waiting请求中。请求完后放入缓存，从缓存获取。

6.Volley网络请求和缓存处理都是在子线程：对响应的处理切换回主线程，构造请求队列时会传入一个结果传递器，会传入一个主线程的Handler参数。

7.为什么volley不适合数据量大的场景：因为http传输的数据，不管是发起请求的数据，还是得到的数据，都会读取到内存中。如果几个线程同时访问数据量大的请求，就容易OOM。

链接：https://blog.csdn.net/fenggering/article/details/88563418
