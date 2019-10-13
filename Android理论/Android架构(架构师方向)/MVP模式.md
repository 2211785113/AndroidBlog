## MVP模式

### 是什么

总：顺序为view-presenter-model-presenter-view，以presenter为中心。

结构图：presenter层与model层互相关联，presenter层与view层互相关联。

- 模型层model：网络请求，数据存取操作。
- 视图层view：用户交互与视图部分。Activity/Fragment/某个view控件。
- 控制层presenter：view层和model层沟通的桥梁，从model层检索数据后返回给view层，使view层和model层之间没有耦合。

### 为什么

- Presenter层完全将Model层和View层分离，主要程序逻辑在Presenter层实现。
- Presenter层与具体的View层没有直接关联，而是通过定义好的接口进行交互，从而在变更View时可以保持Presenter不变， 这点符合面向接口编程的特点。
- View只应该有简单的Set/Get方法，以及用户输入和设置界面显示的内容，除此之外就不应该有更多的内容。绝不允许View直接访问Model，这是其与MVC的很大不同之处，解决了MVC模式model层和view层耦合的情况。

### 怎么样

官网：todo样例
https://github.com/googlesamples/android-architecture
分析：
https://juejin.im/entry/591c1d072f301e006bd1042b

### 用途/后果

项目中应用：

心神学堂：多个presenter，1个总model。

心神视讯：多个model(简单工厂模式)，1个总presenter。

### 快问快答

页面正在请求数据，但是页面突然想要退出这种问题该怎么处理？因为想要退出界面但是还要进行操作会导致页面对象无法释放造成内存泄漏。

如果是Fragment界面，在onCreateView时初始化Presenter，然后让presenter绑定界面即attachView，当onDestroyView的时候取消绑定即detachView。只要是进行model层网络请求数据和view层显示界面都检查是否绑定view即isViewAttached(view)。







