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

### 5.线程间的共享和协作。
ANR场景：
主线程正在sleep睡眠，耗时操作，按back键，主线程有接收到需要处理的请求，ANR。。。
主线程正在网络请求，要更新界面，主线程有接收到需要处理的请求，ANR。。。
解决：子线程。
线程间共享：子线程网络请求完后再在主线程渲染界面。

1.
线程间共享：synchronized内置锁。（访问顺序-同步机制）
解决问题：多个线程访问变量有冲突。
使用：锁的是对象。
代码：ThreadDemo-syn-SynchronizedDemo
定义：Java允许多个线程共享一个对象，和一个对象里的成员变量。
问题：值不对。因为多个线程同时访问同一个变量，导致count++变小了。
解决：用synchronized关键字内置锁。java语言内置的锁。
1. 方法：直接加上关键字。锁的是this，是当前类的对象。
2. 方法内部：synchronized(obj)。定义一个obj对象，直接给obj对象上一个锁。即对象锁。
3. 方法内部：synchronized(this)。锁当前类的实例对象。
用处：同一时刻只能有一个线程处于方法/同步块内。
内化：TimeUnit的使用。代码：SleepTools。
https://blog.csdn.net/dreamzuora/article/details/82664379
使用： TimeUnit.SECONDS.sleep(seconds);
注意：run方法中不需要执行父类的run方法即super.run();
对象锁：锁对象。代码：SynTest。不同的对象锁互不干扰。同一个对象锁干扰。
类锁：锁类的class对象。代码：SynClaAndIns。
类锁和对象锁互不干扰。
两个静态方法加类锁也会像对象锁一样互不干扰。
内化：class对象是JDK里的类加载到虚拟机，虚拟机为每个类生成一个class对象。（翻译——>解释执行。JVM将 Java 文件翻译成 class 字节码文件。）
动态代理
⚠️
（普通成员方法）synchronized锁的是对象，即对象锁；
（成员变量/成员方法）加上static关键字就是锁的类的class对象，即类锁。（情形：static方法加锁 / 静态变量 ）
线程间共享：ThreadLocal的使用。线程的隔离。
解决问题：多个线程之间的静态变量是独有的，需要隔离。
代码：threadLocal
解决问题：多个线程访问同一个变量，导致错乱。
运用：一个变量归自己的线程独有。避免一个变量在多个线程间共享的问题。
具体：ThreadLocal内部的运作。进行了什么操作，以后研究。
Handler也用了ThreadLocal。

2.
线程间协作：wait  notify/notifyAll（通知Object上1个/所有等待的线程）
解决问题：线程的某个条件变化了，但是这个条件不由我自己的线程控制，需要其他线程来通知我，所以需要通知机制。主线程和子线程各做各的，没有干扰。
wait和notify在synchronized方法块内。
流程：
开启km和site子线程，当条件不满足时，线程进入阻塞状态，执行’被监听’语句；
当条件变化时，notifyAll通知并唤醒所有线程。
（阻塞和通知都需要加锁）

UI：循环检查string不为空就显示。
network：网络取数据，写入string。
缺点：循环浪费资源，耗CPU。5ms不合适，根据网络情况定刷新显示，不能保证即时性。
while(String 不为空) {
    sleep(5);
}
解决：主线程和子线程各做各的。等待和通知机制。Android-Handler。
等待和通知的标准范式：
等待方：
1. 获取对象的锁
2. 检查条件，条件不满足wait
3. 条件满足，执行业务代码。
子线程：
syn(对象){
    while(条件不满足){
        对象.wait
        // 调用wait之后，持有的锁就释放了。（面试重点）所以wait方法持有锁通知方还能拿到锁。
    }
    执行业务代码
    // eg：代码被监听了。
}
通知方：
1. 获取对象的锁
2. 修改条件
3. 通知等待方
主线程：
syn(对象){
    执行业务代码，修改条件
    对象.notify/notifyAll
}
注意：yield方法和sleep方法不会释放锁。wait方法会释放当前线程持有的锁。（面试重点）
使用：当线程运行到wait时，线程就阻塞，直到收到notify或notifyAll该线程才被唤醒，继续执行。
解决的问题：多线程并发。主线程和子线程不受干扰。
notify/notifyAll：
一般用notifyAll
因为notify只唤醒一个线程。
代码：waitAndNotify

3.
显示锁Lock接口。（Lock锁和Condition条件）
问题：获取synchronized锁过程不能中断；没有尝试获取锁的机制，要么拿到要么拿不到。属于内置锁。
解决：lock显式获取锁，显式释放锁。属于Java语法层面的锁。
优点：（面试：显式锁的优点）
* 获取锁的过程可以中断（lockInterrupted）
* 可以尝试获取锁（规定时间tryLock）
* 释放锁（unLock）
* 实现（可重入锁ReenTrantLock：允许一个线程反复多次去拿同一把锁。）
公平锁：先申请的线程先拿到锁。公平模式：A B C
非公平锁：后申请的线程先拿到锁。非公平模式：A C B
实际，非公平锁性能更大。
synchronized & reentrantLock：非公平
原因：A拿到锁，B挂起；A释放锁，B唤醒。C要获取锁，非公平模式，C比B先拿到锁。B挂起唤醒一存一取上下文切换。非公平比公平时间周期短很多。非公平减少上下文切换的时间，效率更高。
公平锁设置：new ReentrantLock(true)
synchronized & reentrantLock：排它锁。同一时刻有且只有一个线程获得这个锁。
读写锁：ReentrantReadWriteLock。可重入。读多写少，性能高于排它锁。
代码：ReadWriteLock。
好处：读锁和写锁分离，性能非常高。读场景比写场景多。10:1。
Lock和Condition实现等待通知：
Lock锁：Lock锁比同步锁synchronized更具有优势。
Condition条件：
Condition的await/signal比Object的wait/notify对多线程的休眠和唤醒控制更精细。
同一个锁可以创建不同的Condition。
https://blog.csdn.net/m0_37955444/article/details/79594807【非常棒】
流程：
加Lock锁：开启线程await。（Condition条件休眠）
加Lock锁：线程通知signal。（Condition条件唤醒）

synchronized是干什么的：
定义：内置锁。是一种同步机制，即同一时刻只能有一个线程处于方法/同步块内。
分类：对象锁，类锁。
泛型：用基本类型的包装类。
wait / sleep / yield 的区别：（面试重点）
wait是Object的方法，wait调用后会释放当前线程持有的锁；
sleep是Thread的方法，sleep调用后不会释放当前线程持有的锁；
yield调用后不会释放当前线程持有的锁。
取CPU的逻辑核心数：
int count = Runtime.getRuntime().availableProcessors();


