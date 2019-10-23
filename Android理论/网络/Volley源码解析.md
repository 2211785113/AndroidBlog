## Volley源码解析

说明：请大家自行查看源码加之我的解释进行独立思考。

1.入口-创建请求队列：Volley#newRequestQueue

newRequestQueue方法有4个构造函数，下列依次执行：

- 参数为Context：调用下边构造函数。
- 参数为Context和BaseHttpStack：Android 2.3版本以上用HttpURLConnection的HurlStack；2.2版本及以下用HttpClient的HttpClientStack。
- 参数为Context和Network：创建请求队列RequestQueue，请求队列里放了4个网络调度线程NetworkDispatcher和1个缓存调度线程CacheDispatcher，分别start启动，所以有5个线程在后台运行并等待请求的到来。

2.创建各种Request，并add到请求队列：RequestQueue#add

- 添加请求时，有一个同步块，同一时刻只能有一个请求在执行。
- 如果不可以缓存，直接添加请求到网络调度线程；如果可以缓存，判断之前是否有执行相同的请求且还没有返回结果，如果有，将请求加入mWaitingRequests等待队列，不再重复请求；如果没有，将请求加入缓存队列。
- add方法没有请求网络或对缓存进行操作。当将请求添加到网络请求队列或缓存队列时，在后台的网络调度线程和缓存调度线程轮询各自的请求队列，有请求任务则开始执行。请往下看。

3.缓存调度线程CacheDispatcher#run：

- 先将线程设置为后台线程；
- while(true)循环处理请求，注意这里如果线程中断会抛出一个异常，isInterrupted由true置为false，再调用Thread.currentThread().interrupt()来中断线程。
- 处理请求processRequest，从缓存队列mCacheQueue中取出一个请求，如果请求被取消，就结束请求；然后获取缓存的响应，如果缓存为空，或者过期，并且不在等待队列中时，把请求加入到网络调度线程。如果有缓存响应且没有过期，对数据进行解析并回调给主线程。

4.网络调度线程NetworkDispatcher#run：

- 前两步同上
- 处理请求processRequest，从网络队列mQueue中取出一个请求，如果请求被取消，就结束请求；然后请求网络得到请求数据，如果请求可以缓存并且有缓存响应，将请求加入缓存中，最后对数据进行解析并回调给主线程。
- mNetwork.performRequest(request)：实为BasicNetwork#performRequest，调用HttpStack#performRequest请求网络，根据不同的响应状态码返回不同的NetworkResponse。HttpStack也是一个接口，实现它的两个类分别是前面的HurlStack和HttpClientStack。
- mDelivery.postResponse：调用接口ResponseDelivery中postResponse方法，ctrl+o搜索类，得到ResponseDeliveryRunnable类，实现了Runnable接口在子线程中发送响应内容给主线程。StringRequest#deliverResponse：最后通过接口回调把数据回调给了new StringRequest。

### 原理

Volley请求是基于请求队列的。首先创建请求队列，并启动，里边有4个网络调度线程和1个缓存调度线程，在后台等待请求到来。然后请求队列添加请求，默认可以缓存，如果之前请求过，放等待队列里，没有请求过放缓存队列里。不可以缓存添加到网络队列。在缓存调度线程中，如果有缓存响应且没有过期，对缓存响应并回调给主线程；如果没有缓存响应，加到网络调度线程。在网络调度线程中，请求网络得到响应回调给主线程，需要的话可以把结果存入缓存。

本质：请求获取顺序————先缓存后网络，缓存没有就去网络获取。

![image](https://github.com/2211785113/Blog/blob/master/images/volley.jpg)

Volley分为三类线程，分别是主线程、缓存调度线程和4个网络调度线程。首先请求会加入缓存队列，缓存调度线程从缓存队列中取出请求。如果找到该请求的缓存响应就直接读取缓存的响应并解析，然后回调给主线程；如果没有找到缓存的响应，则将这条请求加入网络队列，然后网络调度线程会轮询取出网络队列中的请求，取出后发送HTTP请求，解析响应并将响应存入缓存，并回调给主线程。

