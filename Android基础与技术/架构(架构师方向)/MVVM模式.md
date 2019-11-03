## MVVM模式

### 是什么

时间：2005年微软架构师提出，2015年谷歌io大会推出MVVM支持库DataBinding。

总：ViewModel跟Model和View进行双向绑定。View发生改变时，ViewModel通知Model更新数据；Model数据更新后，ViewModel通知View更新。

结构图
![结构图](https://github.com/2211785113/Blog/blob/master/images/mvvm.jpg)

- model：
- view：
- viewmodel：


### 为什么

不同于MVP：ViewModel跟Model和View进行双向绑定。

### 怎么样


### 快问快答

谈MVC ，MVP，MVVM三种分层框架设计：

- MVC:View是可以直接访问Model的！从而，View里会包含Model信息，不可避免的还要包括一些 业务逻辑。 在MVC模型里，更关注的Model的不变，而同时有多个对Model的不同显示，及View。所以，在MVC模型里，Model不依赖于View，但是 View是依赖于Model的。不仅如此，因为有一些业务逻辑在View里实现了，导致要更改View也是比较困难的，至少那些业务逻辑是无法重用的。
- MVP：MVP 是从经典的模式MVC演变而来，它们的基本思想有相通的地方：Controller/Presenter负责逻辑的处理，Model提供数据，View负 责显示。作为一种新的模式，MVP与MVC有着一个重大的区别：在MVP中View并不直接使用Model，它们之间的通信是通过Presenter (MVC中的Controller)来进行的，所有的交互都发生在Presenter内部，而在MVC中View会从直接Model中读取数据而不是通过 Controller。
- MVVM：数据双向绑定，通过数据驱动UI，M提供数据，V视图，VM即数据驱动层