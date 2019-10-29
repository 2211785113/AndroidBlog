## Android知识体系

### 目录

- 0.业务(技术服务于业务,理论服务于实践)
- 1.最基础(四大组件,Fragment,Application,Context,SparseArray)
- 2.UI(layout,view,anim,drawable,opengl+编码/渲染)
- 3.进程与线程(handler,RxJava,线程池,IPC)
- 4.开源框架(图片/网络/依赖注入)
- 5.数据存储与通信(数据库,缓存,传递,文件预览)
- 6.适配(UI/横竖屏幕适配)
- 7.架构(模式)
- 8.性能(UI-矢量图绘制不同屏幕下分辨率)
- 9.安全与打包(代码混淆)
- 10.新技术相关(google io大会看法,kotlin,flutter,jetpack,插件化)
- 11.Android SDK与NDK(第三方推送/地图,JNI与NDK编程)
- 12.环境(AS,gradle)
- 13.版本控制(git)
- 14.Android源码学习

### 1.最基础(四大组件,SparseArray)

SparseArray:
定义：类似于HashMap存储数据的类。
区别：
HashMap:采用hash算法决定每个元素的存储位置，存放的是数组元素的引用，通过每个对象的hash值来映射对象。
SparseArray:用数组数据结构来保存映射，然后通过二分查找来找到对象
优点：
提高了内存效率，因为避免了自动装箱，数据结构不依赖额外的entry对象。
用数组数据结构来保存映射，然后通过二分查找来查找key。
适用于数据量不大的场景。
原因：
SparseArray是一种稀疏数组，用稀疏数组表示数据，节省了内存空间,不需要开辟内存空间来额外存储外部映射。
查找key相对应的value值使用了重要的二分查找。
链接：https://blog.csdn.net/weixin_39460667/article/details/81774128

Application和Activity，Service一样都是Android框架的系统组件，当Android程序启动时
系统会创建一个Application对象，用来存储系统的一些信息。
Android系统自动会为每个程序运行时创建一个Application类的对象且只创建一个，
所以Application可以说是单例（singleton）模式的一个类。
Application对象的生命周期是整个程序中最长的，它的生命周期就等于这个程序的生命周期。
因为它是全局的单例的，所以在不同的Activity,Service中获得的对象都是同一个对象。
所以可以通过Application来进行一些，如：数据传递、数据共享和数据缓存等操作。


### 2.UI(layout,view,anim,opengl+编码)

**View**

见我的另一个项目：[View所有知识点]{https://github.com/2211785113/CustomView/blob/master/README.md}

### 3.进程与线程(handler)

消息机制
子线程传递message给主线程：
第一种：
handler.obtainMessage(3).sendToTarget();
单用可行，但是要传递message不行，比如：
Message message = new Message();
message.obj = result;
handler.obtainMessage(3,message).sendToTarget();
同样地，用bundle传递message也不行：
Message message = new Message();
Bundle bundle = new Bundle();
bundle.putString("aaa",result);
message.setData(bundle);
handler.obtainMessage(3,message).sendToTarget();
第二种：
Message message = handler.obtainMessage(3);
message.obj = result;
handler.sendMessage(message);
同样地，用bundle传值也可以：
Message message = handler.obtainMessage(3);
Bundle bundle = new Bundle();
bundle.putString("aaa",result);
message.setData(bundle);
handler.sendMesssage(message);

总结：
不传递数据：
handler.obtainMessage(3).sendToTarget();
传递数据：
Message message = handler.obtainMessage(3);
handler.sendMessage(message);
总之：
就是不能用
Message message = new Message();
handler.obtainMessage(3,message).sendToTarget();

进程间通信
线程间通信

### 4.开源框架(图片/网络)

### 5.数据存储

### 6.适配

### 7.架构

### 8.性能

**内存泄漏**

是什么：对象无法释放导致内存无法释放。

为什么：

造成内存泄漏的例子：

- 强引用
- 内部类持有外部类引用

怎么样：

- 强引用造成内存泄漏用弱引用替代，因为内存泄漏指对象无法释放，用弱引用内存不足时会回收对象。代码如下：
- 内部类持有外部类引用，用静态内部类替代。因为静态内部类不需要调用外部类对象，不持有外部类引用。

用途/后果：

### 9.安全(代码混淆)

### 10.新技术相关(google io大会看法,kotlin,flutter,jetpack)

### 11.Android SDK与NDK

SDK：软件开发工具包。
NDK：本地开发包。
https://www.jianshu.com/p/87ce6f565d37

### 12.环境(gradle)

gradle依赖冲突(依赖重复，之前导包重复问题，结合项目)、依赖检查
https://www.jianshu.com/p/0c914a9b47bf
[系统学习gradle]{https://www.zhihu.com/search?type=content&q=%E5%A6%82%E4%BD%95%E7%B3%BB%E7%BB%9F%E5%AD%A6%E4%B9%A0Android%20gradle}

close项目删除app下build
invalidate and restart:清除缓存

Gradle查看编译错误详细日志：
./gradlew compileDebugSource --stacktrace -info
查看gradle版本：
./gradlew -v

DexArchiveBuilderException：
问题：用1.7和1.8，gson最新包2.8.6都会报错。所以不是java 8和gson最新包不兼容的问题。(具体问题，随后总结思考)
解决：把gson包改为了2.8.2
参考：https://majing.io/posts/10000003611166

导包重复异常：dexMerged，导external中查找包是否重复。


### 13.版本控制(git)

[Git](https://github.com/2211785113/Blog/blob/master/Android技术/版本控制/Git.md)