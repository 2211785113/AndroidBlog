## Android基础知识

### 目录

- 1.最基础(四大组件,注解,SparseArray)
- 2.UI(layout,view,anim,opengl+编码)
- 3.进程与线程(handler)
- 4.开源框架(图片/网络)
- 5.数据存储
- 6.适配
- 7.架构
- 8.性能
- 9.安全(代码混淆)
- 10.新技术相关(google io大会看法,kotlin,flutter,jetpack,插件化)
- 11.Android SDK与NDK
- 12.环境(gradle)
- 13.版本控制(git)

### 1.最基础(四大组件,注解,SparseArray)

注解：
定义：代码里的特殊标记。这些标记可以在编译、类加载、运行时被读取，并执行相应处理。
作用：
* 在不改变原有逻辑的情况下， 在源文件中嵌入一些补充信息。
* 代码分析工具、开发工具和部署工具可以通过这些补充信息进行验证、 处理或部署。
分类：
* 标准注解如覆盖超类中的方法@override，废弃方法，警告等。
* 元注解如注解其他注解，从而创建新的注解。
注解的定义：
* 定义注解：接口关键字
* 定义成员变量：无形参方法，及使用注解，成员变量有默认值。
* 定义运行时注解和编译时注解：设定注解保留策略@Retention。
编写注解处理器。
* 运行时注解采用反射机制处理；如retrofit的get方法。
* 编译时注解采用 AbstractProcessor 来处理。
依赖注入的作用：使对象之间解耦合。
依赖注入的三种实现方式：
* 构造方法注入；
* Setter方法注入；
* 接口注入。
依赖注入框架的作用：
* 使对象之间解耦合；
* 完成复杂的依赖。
ButterKnife原理解析：三个步骤。
Dagger2原理解析：三个步骤。

注解的定义，作用，分类；✔️
定义注解，定义注解成员变量；定义编译时注解和运行时注解。✔️
依赖注入的原理。✔️
依赖注入框架ButterKnife的原理。✔️
依赖注入框架Dagger2的原理。✔

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

gradle依赖冲突、依赖检查
https://www.jianshu.com/p/0c914a9b47bf

close项目删除

### 13.版本控制(git)

