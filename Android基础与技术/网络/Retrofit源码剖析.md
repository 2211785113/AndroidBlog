## Retrofit源码剖析

说明：请大家自行查看源码加之我的解释进行独立思考。

关联知识点：数据结构-数组，Java基础-并发/队列集合，Android基础与技术-数据缓存，设计模式-单例。

### 1.入口-通过建造者模式构建Retrofit对象

Retrofit#Builder内部类：

构造方法：Platform#get：使用单例模式，最终调用findPlatform方法，根据不同的运行平台来提供不同的线程池。

build方法：

- baseurl必须指定；
- callFactory是构建Retrofit对象调用callFactory方法传进来的，如果为空，新建一个OkHttpClient对象。所以如果需要对 OkHttpClient 进行设置， 则可以构建 OkHttpClient 对象， 然后调用callFactory方法将设置好的OkHttpClient传进去。
- callbackExecutor：用来将回调传递给 UI 线程。
- adapterFactories：主要用于存储对Call进行转化的对象
- converterFactories：主要用于存储转化数据对象。例如增加返回值为gson的支持，设置返回的数据转化为gson对象。

### 2.创建Call（还需要多看多多理解，学习源码可以画一个类的结构图）

- retrofit实例调用create方法生成接口(真实主题)的动态代理对象Proxy.newProxyInstance()。
- 调用IpService的getIpMsg方法时，最终会调用InvocationHandler的invoke方法。invoke方法有三个参数：代理对象；调用的方法；方法的参数。loadServiceMethod(method)中的method即为定义的getIpMsg方法。
- loadServiceMethod：从 serviceMethodCache 查询传入的方法是否有缓存；如果有，就用缓存的ServiceMethod；如果没有，就创建一个，并加入serviceMethodCache 缓存起来。如果result为null，执行下一步。
- 构建ServiceMethod：最后调用HttpServiceMethod.parseAnnotations方法。createCallAdapter方法，得到在构建Retrofit调用build方法时callAdapterFactories添加的对象的get方法。adapterFactories 列表默认会添加 defaultCallAdapterFactory 即 ExecutorCallAdapterFactory。get方法会得到CallAdapter对象，CallAdapter的responseType方法会返回数据的真实类型，adapt 方法会创建ExecutorCallbackCall，它会将call的回调转发至UI线程。
- 构建ServiceMethod：调用CallAdapter的responseType得到的是返回数据的真实类型。
- 构建ServiceMethod：调用createResponseConverter方法来遍历converterFactories列表中存储的Converter.Factory，并返回一个合适的Converter用来转换对象。构建Retrofit时我们指定了Gson转换并添加到了converterFactories列表中，表示返回的数据支持转换为JSON对象。
- 构建ServiceMethod：遍历parseMethodAnnotation方法来对请求方式(比如GET、POST)和请求地址进行解析，对方法中的参数注解进行解析(比如@Query、@Part)，最后创建ServiceMethod类并返回。
- 创建OkHttpCall：构造方法进行赋值，然后调用serviceMethod.callAdapter.adapt(okHttpCall)，callAdapter的adapt方法会创建ExecutorCallbackCall， 并传入OkHttpCall。
- ExecutorCallbackCall：是对Call的封装，添加了callbackExecutor线程池将请求回调到UI线程。得到Call对象后调用enqueue方法，即ExecutorCallbackCall#enqueue方法，最终调用delegate#enqueue，delegate传入OkHttpCall参数。

### 3.Call#enqueue

即调用OkHttpCall#enqueue：调用parseResponse方法，根据返回的不同状态码code值来做不同的操作。调用convert方法来转换为特定的数据格式。因为添加的是返回值为gson的支持，所以看GsonConverterFactory源码，方法responseBodyConverter会创建GsonResponseBodyConverter：convert方法会将回调的数据转换为JSON格式。

总：OkHttp来请求网络，将返回的Response进行数据转换并回调给UI线程。
