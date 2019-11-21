## UI

### 目录

- 1.View
- 2.布局layout
- 3.控件widget
- 4.图片drawable
- 5.动画anim
- 6.通知Notification
- 7.窗口Window
- 8.对话框Dialog
- 9.openGL

### 1.View

[我的github项目之View](https://github.com/2211785113/CustomView/blob/master/README.md)

**事件分发机制：**

事件传递：

- Activity对象创建完毕，会将DecorView添加到Window中，同时创建ViewRootImpl，来渲染视图，并将DecorView和ViewRootImpl关联起来。
- Activity中会有dispatchTouchEvent()方法：因为事件传递是自上而下的，如果下层没有处理，则会由上层来处理，执行上层的onTouchEvent方法。

原理：

- 事件传递顺序3个重要的方法：dispatchTouchEvent()是否消耗了本次事件，onInterceptTouchEvent()是否拦截了本次事件，onTouchEvent()是否处理本次事件。
- ViewGroup#dispatchTouchEvent伪码：如果是down事件，首先会清除触摸标志位；再判断如果是down事件，有没有设置标志位，设置了标志位，则向子View传递，没有设置标志位，执行onInterceptTouchEvent方法，值为true父View拦截事件，值为false向子View传递；如果不是down事件，因为同一个事件要么由父控件来执行，要么由子控件来执行，down事件是父控件来执行，那么move事件和up事件也应该由父控件来执行。
- 触摸/touch事件分发机制：View的onTouch，onClick，onTouchEvent的执行顺序：onTouch，onTouchEvent，onClick
- 第一次父View#onITE返回true拦截事件，打印依次执行disPTE->onITE->onTE；第二次返回true拦截事件后执行disPTE和onTE。(实践代码见我的另一个github项目：Android_SlidingConflict # OuterTestActivity类；知识点：同一个事件不再执行onITE？运用到哪里了吗？)

用途/后果：

滑动冲突：

- 同方向滑动冲突。如ScrollView和ListView，可以计算ListView高度而动态设置ListView的高度，ScrollView高度可变。
- 不同方向滑动冲突。如SRL和VP，SRL源码中当滑动距离大于最小滑动距离，会拦截事件，但是当VP左下或右下滑动时，事件就会被父View拦截，造成滑动冲突。所以用外部拦截法和内部拦截法来解决滑动冲突。外部拦截法事件向子View传递，只有当Y轴大于X轴滑动距离时，拦截事件；内部拦截法，用rDTE来拦截事件。

**实现原理：**


**自定义View：**

测量：计算视图大小。

- View的测量过程：先执行measure方法，再执行onMeasure方法，最后的结果为MeasureSpec的specSize。系统会将View本身的LayoutParams根据父容器添加的规则转换成相对应的MeasureSpec，再根据MeasureSpec测量出View的宽高。注意自定义View时，直接继承自View和ViewGroup的View需要处理wrap_content。因为View在布局中使用wrap_content，那么它的specMode是AT_MOST即parentSize模式，在这种模式下，它的宽高等于specSize，和matchParent效果一致，显示不正确。所以当MeasureSpec的mode为AT_MOST时，给View设置对应的宽高数值。宽度设置为200dp/match_parent/wrap_content怎么进行测量。
- ViewGroup的测量过程：onMeasure方法中会对所有的子元素进行measure过程，这个时候measure流程就从父容器传递到子元素中了，这样就完成一次measure过程，接着子元素会重复父容器的过程，如此反复就完成了整个View树的遍历。

布局：提供视图要显示的位置。

绘制：绘制。onDraw方法中自定义View直接继承View和ViewGroup需要处理下padding。

View和SurfaceView的区别：View基于主线程刷新UI，SurfaceView子线程可以刷新UI

invalidate和requestLayout的区别：invalidate是重新绘制，onMeasure和onLayout都不会被调用，会重绘标记PFLAG_INVALIDATED的view。requestLayout是对View重新布局，会执行自身或父View的onMeasure和onLayout方法，如果layout中布局改变可能会执行onDraw方法。

定义：View为所有图形控件的基类，View的绘制由3个函数完成

例子：

- 如何实现一个字体的描边与阴影效果：阴影用 xml 或代码设置；描边上下两层TextView通过不同的字体颜色叠加。
- 如何画出一个印章的图案：链接代码在电脑seal项目中。绘制圆环-canvas.drawCircle，绘制五角星-canvas.drawPath，绘制字体-圆弧用角度表示。https://blog.csdn.net/loser_li/article/details/48005683

移动：

- 三种方式：scrollTo/scrollBy，动画平移，Layoutparams。

弹性滑动：

- 三种方式：Scroller，
️
### 2.布局layout

线程布局LinearLayout：weight的使用场景

相对布局RelativeLayout：

约束布局ConstraintLayout：优势。 Google新生支持库。https://blog.csdn.net/guolin_blog/article/details/53122387

用途/后果：让两个TextView左右水平居中。

单个布局和多个嵌套布局。

margin和padding的区别。️️

match parent和any size的区别：

* match parent是用于填充满当前控件的父布局；
* any size是用于填充满当前控件的约束规则

例子：给你一个布局，你怎么写，左边是封面，右边是主标题和子标题，下面是正文。️
例子：中间有一条竖线。️

### 3.控件widget

RecyclerView：

adapter里的方法：getItemCount，getItemViewType，onCreateViewHolder，onBindViewHolder。

优化：BaseRecvAdapter。自定义LayoutManager。

分页请求：

- RecyclerView分页请求
- ViewPager分页请求

RecyclerView和ListView的区别(优点)：

- 缓存:前者缓存的是View+ViewHolder+flag，不用每次调用findViewById,后者则只是缓存View
- 刷新数据：前者提供局部刷新，后者则全部刷新

recyclerview缓存机制，listview的缓存机制，相同条目复用，会出现数据错乱。

RV和LV优势在哪里？完美替换为什么LV没有过时。

recyclerview复用：抖音无限循环效果实现，心神学堂在线作业题翻页。

recyclerView嵌套卡顿解决如何解决：

- 设置预加载的数量LinearLayoutManager.setInitialPrefetchItemCount(4)，默认是预加载2个，
- 设置子项缓存，设置自带滑动冲突解决属性rv.setHasFixedSize(true);        rv.setNestedScrollingEnabled(false);
- 可以完美解决，不过Google不推荐RecyClerView嵌套使用,需要嵌套尽量找类似于ExpandableListView 第三方控件来解决

上拉加载：recyclerview的adapter中先执行getItemCount方法，当添加了foot布局的时候，count+1；getItemViewType方法，添加了foot布局并且position的位置为总数量-1即滑动到最后一个的时候，返回类型为Type_foot；onCreateViewHolder中初始化底部布局，创建一个新的ViewHolder；onBindViewHolder中负责绑定数据。recyclerview有一个滑动监听，滑动到最后一个且停止滑动时，请求分页加载数据传给adapter。

链接：https://blog.csdn.net/qq_38356174/article/details/90716344

下拉刷新：SwipeRefreshLayout，PullRefreshLayout。

### 4.图片drawable

### 5.动画anim

动画基础：

View动画和属性动画：

* View动画：四种简单变换，平移旋转缩放透明度。作用对象为View或ViewGroup。
* 属性动画：可以做出更多的动画效果。作用对象为任何对象包括View，也可以没有对象。

例子：

* View动画：ViewGroup指定动画控制子元素的动画效果；Activity或Fragment实现切换动画效果。
* 属性动画：实现匀速动画和非匀速动画。例子：给一个Button按钮实现宽度从0到500px的动画效果。

Android子线程也可以实现动画。可以在子线程给View设置动画，动画的更新在主线程。

链接：https://www.cnblogs.com/futureli/p/4621867.html

### 6.通知Notification

### 7.窗口Window

### 8.对话框Dialog

### 9.openGL

是什么：渲染2D，3D图像。

底层：图形渲染机制-可能是多缓冲区。

视频渲染时，假设屏幕上要显示1这个数字，视频要显示1~2这个数字切换，(图1)由于cpu处理性能不够强，有一个缓冲区里面有2，cpu正在往里面放东西，但是由于数据比较慢，2正拷贝了一半，(图2)，这时cpu可以刷新一帧了，于是屏幕成了图3。提示：cpu不单单可以关联到显存上也可以关联到内存上。

解决：cpu可以关联到内存上，所以开辟两个缓冲区，(图4)，当1缓冲区写好数据以后，GPU渲染第一个缓冲区里的图像，缓冲区2写好以后，GPU渲染缓冲区2里的图像。


