## MVC模式

### 是什么

总：控制层操作model层数据，返回给view层显示。

结构：控制层指向model层，view层。model层和view层互相指向。
![结构图](https://github.com/2211785113/Blog/blob/master/images/mvc.jpg)

- 模型层model：网络请求，数据结构相关等，针对业务。
- 视图层view：xml或代码(js/html等)表示界面。
- 控制层controller：Activity、Fragment等。

### 为什么

- 分离业务逻辑(控制层)，数据请求(model层)，界面显示(view层)。
- 改进界面(View层)，不需要再重写业务逻辑代码(控制层)。

### 用途/后果

缺点：

- Activity不是一个标准的MVC模式中的控制层，它的首要职责是加载应用布局和初始化用户界面，接受并处理来自用户的操作请求，进而做出响应。随着界面及其逻辑的复杂度不断提升，Activity类的职责不断增加，以致变得庞大臃肿。

- Model层和View层相互耦合，不易开发和维护。

解决：MVP，MVVM
