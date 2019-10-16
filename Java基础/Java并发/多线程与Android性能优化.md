## 多线程与Android性能优化

### 1.基础概念

操作系统与硬件：

CPU核心数与线程数：4核CPU同时运行4个线程。用intel超线程技术，4核CPU可以同时运行8个线程。

CPU时间片轮转机制：高低峰。RR调度。new Thread。1个CPU，100线程，一个线程5ms，100个线程500ms。

进程和线程：
* 进程：操作系统资源分配的最小单位。一个app就是一个进程。比较独立，和CPU没有关系。
* 线程：CPU调度的最小单位。共享一个进程内的资源。
* CPU——》操作系统
* 线程——》进程
* Java不能用CPU指定线程，C可以。
* 关系：进程大于线程，线程依附于进程存在。线程会共享进程内的资源。
* 线程：APP MainThred=UIThread，负责绘制UI界面。耗时操作执行5s，会造成ANR。所以有了Work Thread子线程。Linux最多开1000个线程，window最多2000个。
* 注意：耗时操作在主线程执行会造成ANR，但是主线程可以执行休眠sleep操作即Thread.sleep(5000)；？？？
* 深度思考：耗时操作不一定会引起ANR。当主线程接收到有需要处理的请求的时候，会造成ANR。例如主线程正在休眠，按返回键就会造成ANR。但是当主线程sleep没有接收到请求的时候就不会造成ANR。
* ANR：应用没有响应，应用不会处理或不想处理你的请求。
* 非常棒的链接：https://www.jianshu.com/p/dc282d0d5ed3
* Java里能不能用CPU指定线程：不能。Java不能干预，C才可以。（操作系统研究到位，百度面试）

并行和并发：（面试高频点）
* 并行：高速公路可以同时运行4辆车。4核CPU并行度为8。
* 并发：单位时间内的并发量。

高并发编程：
* 定义：多核多CPU。
* 好处：
* 充分利用CPU，提高响应时间。迅雷：多线程下载快。
* 程序开发模块化，简单化，异步化。多个线程，下载图片放在work thread，简化ui thread里的代码。
* 注意：
* 安全性。线程共享进程内的资源。线程安全问题；死锁；操作系统开的线程数有限。
* 每个线程有一个栈空间，开一个栈消耗1M的内存，可能造成死机。线程要消耗资源，保存数据到内存或硬盘，其他线程一存一取耗费时间即上下文切换，一个上下文切换（操作系统术语）大概消耗2万个CPU的时间周期。所以要用线程池。（操作系统，计算机硬件）总：声明线程，消耗内存。

### 2.Java里的线程

Java里的程序天生就是多线程。

用ManagementFactoty获取JVM运行消息：线程使用情况，占用内存情况。（程序见TheadDemo-java，发现一个简单的方法启动了6个线程）
Android中也可以获取内存信息。

6个线程：
main：主线程
Finalizer线程：负责资源回收。重写object的finalize方法。
* 在finalize方法里写的资源回收动作虚拟机不一定来执行。
* 因为：Finalizer是守护线程，主线程和守护线程同生共死，主线程关闭，Finalizer线程就跟着关闭了。
GC线程：内存不足时才启动。

### 3.启动多线程：（ThreadDemo-newThread）
* 扩展Thread类
* 实现Runnable接口
* 有返回值：实现Callable接口（AsyncTask里会用到）

Runnable和Callable是线程吗？
* 不是。Thread类是对线程的抽象。Runnable和Callable是对任务的抽象。
一个线程只有一个Runnable吗？
* 不是。线程池里有很多个线程，可以有很多个任务提交到一个线程。
Callable先执行？
* 不对。线程的调度顺序无法确定，取决于操作系统对线程的调度。

### 4.手动终止线程：？？？死锁定义？？？（ThreadDemo-EndThread）
正常：run方法跑完，抛出异常。
弃用：
* stop：终止。不管锁有没有释放，立马杀死线程。
* suspend：挂起线程。拿着锁挂起，浪费资源，造成死锁，
* resume：挂起后重启线程。
API：
* interrupt：成员方法。中断线程。它是协作式，而不是抢占式。
* 调用interrupt进入了就绪状态而没有进入死亡状态。
* 为什么不是进入运行状态？运行状态由操作系统决定，操作系统分配CPU给线程。
* interrupted：静态方法。判断线程是否被中断。
* isInterrupted：判断线程是否被中断。
* 是Thread类的方法，Runnable不能执行这个方法。
* 解决：Runnable里用Thread.currentThread().isInterrupted方法。
例子：
* a线程调用b线程的interrupt方法，不是要终止b线程，而是和b线程打个招呼。b是否中断，看自己的run方法。
* b在run方法里判断其他人是否要终止我的线程。
注意：新建完线程要启动的。
区分协作式和抢占式：
* 协作式：线程里操作才能中断线程，不进行任何操作不会中断。用途：线程的使用者决定是否终止线程，不由外界控制。
* 抢占式：window命令强制关闭线程，linux使用kill -9 pid命令。
区分 isInterrupted 和 interrupted：
interrupted 除了有 isInterrupted 的作用，可以把 flag 由 false 改为 true，还可以把 flag 由 true 改为 false。
注意：isInterrupted是Thread里边的方法。不能在run方法里运行。
JDK里能不能强制中止一个线程：（58同城面试）
* 我能强制中止，但是stop方法已经被JDK废弃，不提倡使用。？？？？可以使用Thread的interrupt方法，但是Java里面线程中断是协作式任务，所以interrupt方法不代表线程会中断，线程是否中断还是由自己来决定。
深度思考：stop被废弃。运行代码ThreadDemo-stopThread。
结果：stop：不管锁有没有被释放，立马杀死线程，破坏数据的完整性。
https://blog.csdn.net/a158123/article/details/78776145
运行结果和链接不同。我觉得我的是对的。

深入理解start和run：代码：ThreadDemo-startandrun
* run方法：普通的成员方法。可以重复执行，也可以单独调用。
* start方法：真正和操作系统的线程挂起钩来，才真正启动线程。只能调用一次，如果写了两遍也只能同时调用一次。
join：任务插队。指定线程插入到当前线程。代码：ThreadDemo-join。（面试，需要自己看）?????看不懂，以后再看
使用：b.join，b就是指定线程。
如何让两个线程顺序执行：join方法。
线程优先级设置线程的执行顺序不一定。它是由操作系统决定的。
执行顺序用join。还有canlunch也可以决定执行顺序。
wait不能保证顺序。
yield：原生。JDK实现。当前线程让出CPU的执行权告诉操作系统。？？？？？
作用：将线程从运行转到可运行状态。
start：就绪告诉操作系统可以运行。（面试：默写五种状态）
setDemon：在线程启动之前，将当前线程设置为守护线程。
问：为什么interrupt之后线程进入了就绪状态。
答：因为interrupt之后发出了一个中断信号，是否中断还是由使用者自己决定。
问：运行状态由谁决定？
答：运行状态由操作系统决定，操作系统分配了CPU线程就会处于运行状态。
生产者消费者模式：BlockingQueue。
为什么sleep只抛出异常而不终止线程。代码：ThreadDemo-UseSleep。(面试)
答：
线程异常中止，所以sleep要抛异常。
抛出异常意味着：
检查中断标志位是否为true，如果为true，则抛出异常，并清除中断标志位，由true改为false。
如果要终止线程，还要再加一个interrupt方法。
抛出异常：清除中断标志位，由true改为false，如果要终止线程，要调用interrupt方法，由false改为true。
Object#wait
Thread#sleep
都会抛出InterruptedException。
sleep和wait的区别：
sleep：线程的休眠。
wait：线程等待。等待线程所需要的资源满足再唤醒。

