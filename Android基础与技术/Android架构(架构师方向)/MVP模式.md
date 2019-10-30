## MVP模式

### 是什么

总：顺序为view-presenter-model-presenter-view，以presenter为中心。

结构图：presenter层与model层互相关联，presenter层与view层互相关联。
![结构图](https://github.com/2211785113/Blog/blob/master/images/mvp.jpg)

- 模型层model：网络请求，数据存取操作。
- 视图层view：用户交互与视图部分。Activity/Fragment/某个view控件。
- 控制层presenter：view层和model层沟通的桥梁，从model层检索数据后返回给view层，使view层和model层之间没有耦合。

### 为什么

- Presenter层完全将Model层和View层分离，主要程序逻辑在Presenter层实现。
- Presenter层与具体的View层没有直接关联，而是通过定义好的接口进行交互，从而在变更View时可以保持Presenter不变， 这点符合面向接口编程的特点。
- View只应该有简单的Set/Get方法，以及用户输入和设置界面显示的内容，除此之外就不应该有更多的内容。绝不允许View直接访问Model，这是其与MVC的很大不同之处，解决了MVC模式model层和view层耦合的情况。

### 怎么样

[官网：todo样例]{https://github.com/googlesamples/android-architecture}

[分析]{https://juejin.im/entry/591c1d072f301e006bd1042b}

### 用途/后果

项目中应用：

心神学堂：多个presenter，1个总model。

心神视讯：多个model(简单工厂模式)，1个总presenter。

### 快问快答

页面正在请求数据，但是页面突然想要退出这种问题该怎么处理？因为想要退出界面但是还要进行操作会导致页面对象无法释放造成内存泄漏。

如果是Fragment界面，在onCreateView时初始化Presenter，然后让presenter绑定界面即attachView，当onDestroyView的时候取消绑定即detachView。只要是进行model层网络请求数据和view层显示界面都检查是否绑定view即isViewAttached(view)。



mvp的优点和缺点：
优点：
降低耦合度；模块职责划分明显；利于测试驱动开发；
代码复用；隐藏数据；代码灵活性。
缺点：
由于对视图的渲染放在了Presenter中，所以视图和Presenter的交互会过于频繁。还有一点需要明白，如果Presenter过多地渲染了视图，往往会使得它与特定的视图的联系过于紧密。一旦视图需要变更，那么Presenter也需要变更了。


mvp mvc mvvm异同。⭕️
mvp的优点和缺点。✔️
项目怎么选框架：MVP和MVVM。⭕️
Android分层框架设计。⭕️

谈MVC ，MVP，MVVM
MVC:View是可以直接访问Model的！从而，View里会包含Model信息，不可避免的还要包括一些 业务逻辑。 在MVC模型里，更关注的Model的不变，而同时有多个对Model的不同显示，及View。所以，在MVC模型里，Model不依赖于View，但是 View是依赖于Model的。不仅如此，因为有一些业务逻辑在View里实现了，导致要更改View也是比较困难的，至少那些业务逻辑是无法重用的。
MVP：MVP 是从经典的模式MVC演变而来，它们的基本思想有相通的地方：Controller/Presenter负责逻辑的处理，Model提供数据，View负 责显示。作为一种新的模式，MVP与MVC有着一个重大的区别：在MVP中View并不直接使用Model，它们之间的通信是通过Presenter (MVC中的Controller)来进行的，所有的交互都发生在Presenter内部，而在MVC中View会从直接Model中读取数据而不是通过 Controller。
MVVM：数据双向绑定，通过数据驱动UI，M提供数据，V视图，VM即数据驱动层





