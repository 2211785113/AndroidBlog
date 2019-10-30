## JavaSE基础知识

### 目录

- 1.数据类型
- 2.运算符-位运算
- 3.String字符串
- 4.控制流程
- 5.对象与类
- 6.继承(反射)
- 7.接口、lamada表达式与内部类
- 8.注解
- 9.异常
- 10.泛型
- 11.集合
- 12.并发

### 1.数据类型

**Java8种基本数据类型**

byte(1byte) short(1byte) int(16位处理器2byte/32位处理器4byte) long(32位处理器4byte，64位处理器8byte)

float(4byte) double(8byte) char(2byte) boolean(1byte)

boolean例子：代码-ABoolean

---

**基本类型与包装类**

链接：https://blog.csdn.net/jinknow/article/details/80246695

**Java有了基本类型的包装类为什么还要有基本类型？**

Integer是对象，存储在堆里，使用new关键字在堆中分配存储空间，通过栈中引用使用这个对象，但是对于简单的小的变量，不是很高效，所以直接将对象存储在栈中，直接赋值，比较高效。所以有了基本类型。

**Java有了基本类型为什么还要有基本类型的包装类？**

Java是面向对象语言，基本类型不具有对象的性质，为了让基本类型有对象的性质，就出现了包装类型。比如使用集合类型Collection时就一定要使用包装类型而非基本类型，它相当于将基本类型“包装起来”，使其具有对象的性质，并且为其添加了属性和方法，丰富了基本类型的操作。

另外，当需要往ArrayList，HashMap中放东西时，像int，double这种基本类型是放不进去的，因为容器都是装object的，这是就需要这些基本类型的包装器类了。

泛型也需要基本类型的包装类。

---

Java的引用类型有哪几种，说说区别？

四种：强引用，软引用，弱引用，虚引用。

区别：

* 强引用：直接的对象引用。内存不足时会抛出异常。
* 软引用SoftReference：当一个对象只有软引用存在时，系统内存不足时此对象会被gc回收。内存不足时先检查软引用对象，回收软引用对象，如果内存还是不足，才会抛出异常。
* 弱引用WeakReference：当一个对象只有弱引用存在时，此对象会随时被gc回收。
* 虚引用：任何时候都能被回收，就像没有引用一样。

应用：

- 软应用：图片缓存框架缓存图片。有内存时就保留缓存，内存不足时就清理掉。
- 弱引用：ThreadLocal中的key。代码例子：
```
private WeakReference<Context> mContext = new WeakReference<>(mContext);
```

### 2.运算符-位运算

**是什么**

Java最有效率的算法。仅限二进制运算。

**为什么**

位运算直接操控二进制，节约内存，使程序速度更快。

**怎么样**

六种：
- &：按位与
- |：按位或
- ^：按位异或
- ~：取反
- <<：左移，相当与*2
- (>>)：右移，正数高位补0，负数由计算机决定

**用途/后果**

1.按位与&：

定义：按二进制进行与运算。

例子：

* n&1==1，n为奇数。因为奇数二进制末尾为1，1的二进制为1，1&1=1
* n&1==0，n为偶数。因为偶数二进制末尾为0，1的二进制为1，0&1=0

对比：

* n%2==1，n为奇数。
* n%2==0，n为偶数。

效率：n&1比n%2高效。

运用：

* 判断奇偶数，奇数得1，偶数得0。
* 获取一个数二进制的最后一位。

3.按位异或^：

定义：按二进制进行异或运算。

特点：

* 交换律：a ^ b ^ c <=> a ^ c ^ b
* 结合律：0异或任何数为任何数： 0 ^ n => n
* 结合律：相同的数异或为0：n ^ n => 0
* a，b两个值相同，异或结果为0；a，b两个值不同，异或结果为1。

所以：

* 异或同一个数两次，原数不变。
* 剩余的数异或同一个数两次，原数不变。

例子：

* B^A^A => B（重复数字为A）
* (A^B)^(A^A^B) => A^B^B => A
* 2^3^2^4^4 => 2^2^4^4^3 => 0^0^3 => 3（找出唯一不重复的数字3）

5.左移<<：

n<<=1即n=n<<1

含义：将n的二进制顺序左移1位，最低位补二进制0，并将结果赋值为n。左移相当于*2。例如：2<<3=2的3次方=8

6.右移>>：

n>>=1即n=n>>1

含义：将n的二进制顺序右移1位，最高位补二进制0，并将结果赋值为n。右移相当于/2。例如：8>>1=4。

**三个常用**

- n&1相当于n%2
- n>>1相当于n/2
- n<<1相当于n*2

**算法**

见其它算法-位运算算法。

### 3.String字符串

**String、StringBuffer、StringBuilder的区别**

Java 平台提供了两种类型的字符串：String和StringBuffer/StringBuilder，它们可以储存和操作字符串。其中String是只读字符串，也就意味着String引用的字符串内容是不能被改变的。而StringBuffer和StringBulder类表示的字符串对象可以直接进行修改。StringBuilder是JDK1.5引入的，它和StringBuffer的方法完全相同，区别在于它是单线程环境下使用的，因为它的所有方法都没有被synchronized修饰，因此它的效率也比StringBuffer略高。

---

**==和equals的区别**

==：

基本数据类型：比较值。例子：比较字符：'a'=='b'；检测字符串的长度是否为0：str.length()==0。

引用数据类型：比较对象在内存中的存放地址(即堆内存地址)。例子：判断字符串是否为空：str==null；比较类对象：e.getClass()==Employee.class（e为一个类对象）。

equals：

追根溯源，是Object类中的一个方法，在该类中，equals的实现也仅仅只是比较两个对象的内存地址是否相等，但在一些子类中，如：String、Integer 等，该方法将被重写。如字符串用来比较内容是否相等。

例子：检测字符串是否相等：a.equals(b)；检查字符串是否为空值：str.equals(“”)。

Object#equals源码：
```
public boolean equals(Object obj) {
        return (this == obj);
    }
```

>以String来举例：
```
String str1 = "abc";
String str2 = "abc";

System.out.println(str1 == str2);
System.out.println(str1.equals(str2));

String str2 = new String("abc");
System.out.println(str1 == str2);
System.out.println(str1.equals(str2));
```
结果分别为：true，true，false，true

>在这里需要理解JVM处理String的一些特性。Java的虚拟机在内存中开辟出一块单独的区域，用来存储字符串对象，这块内存区域被称为字符串缓冲池。当使用 String a = "abc"这样的语句进行定义一个引用的时候，首先会在字符串缓冲池中查找是否已经相同的对象，如果存在，那么就直接将这个对象的引用返回给a，如果不存在，则需要新建一个值为"abc"的对象，再将新的引用返回a。String a = new String("abc");这样的语句明确告诉JVM想要产生一个新的String对象，并且值为"abc"，于是就在堆内存中的某一个小角落开辟了一个新的String对象。

String#equals源码：
```
public boolean equals(Object anObject) {
        //如果比较的对象与自身内存地址相同，
        //说明引用类型指向同一个对象，
        //则equals的返回值跟==的结果一样。
        if (this == anObject) {
            return true;
        }

        //如果比较的对象与自身内存地址不同，
        //并且比较的对象是String类型时，
        //将执行以下代码
        if (anObject instanceof String) {
            String anotherString = (String) anObject;
            int n = length();
            if (n == anotherString.length()) {
                int i = 0;
                while (n-- != 0) {
                    //只有两个字符串中有一个字符不相等，就返回false。
                    if (charAt(i) != anotherString.charAt(i))
                        return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }
```
>理解equals存在的必要性：==与equals的主要区别是：==常用于比较原生类型，而equals()方法用于检查对象的相等性。另一个不同的点是：如果==和equals()用于比较对象，当两个引用地址相同，==返回true。而equals()可以返回true或者false主要取决于重写实现。最常见的一个例子，字符串的比较，不同情况==和equals()返回不同的结果。equals()方法最重要的一点是，能够根据业务要求去重写，按照自定义规则去判断两个对象是否相等。重写equals()方法的时候，要注意一下hashCode是否会因为对象的属性改变而改变，否则在使用散列集合储存该对象的时候会碰到坑！！理解equals()方法的存在是很重要的。

---

**例子**

判断String对阵的字符串是否相同：AStringSame

### 4.控制流程

**Switch语句的case标签**

- 类型为：byte short int char 的常量表达式，long不可以
- Java 5：枚举类型
- Java 7：字符串类型

---

**break,continue,return的区别**

- break：终止循环
- continue：终止本次循环，继续下一次循环
- return：退出整个函数/方法

### 5.对象与类

**面向对象三大特征**

封装：封装类中的域（封装数据和行为），使外部可以访问

继承：构造新类（扩展类），复用类的方法和域，添加新的方法和域。

多态：

* 一个对象变量可以指示多种实际类型的对象。
* 对象变量是多态的，子类对象可以赋给超类变量
* 对象变量可以引用超类对象，也可以引用任何一个子类对象
* 多态期望的作用是任何使用父类对象的地方都能够透明的使用子类对象。

**面向对象思想实操：一段程序代码描述我们所在的这间房间**

封装：ThisRoom。封装房间的长宽高属性。

继承：ThisRoom extends Room。这间房间继承房间。房间是抽象概念，这间房间是具体概念。

多态：ClientRoom。Room中通水通电，客户端用多态来实例化对象，并调用通水通电方法。

注意：桌子椅子不能继承房间，因为不属于同类，桌子椅子和房间是关联关系。

类之间的关系：依赖聚合继承，分别使用耦合，包含，特殊与一般来表示。

---

**对象和类的区别**

- 类是对某一类事物的描述，是抽象的；而对象是一个实实在在的个体，是类的一个实例。比如人是一个类，教师是人的一个实例。
- 类是一组具有相同属性的对象集合体。
- 类的数据值是共享的，一个实例属于单个对象，能访问它所属类的类数据值，不同对象还会有不同的数据值。
- 先有类，才有类的实例-对象。
- 实例方法属于单个对象，类方法属于类。

**类序列化的目的**

链接：https://blog.csdn.net/u010002184/article/details/90755625

类实现Serializable就代表类序列化。Java对象转换成字节序列的过程就称为对象的序列化。无论何种类型的数据，都是以二进制的形式在网络上传送，为了由一个进程把Java对象发送给另一个进程，需要将其转换为字节序列才能在网络上传送。所以序列化的作用是为了不同JVM之间共享实例对象的一种解决方案，即实现进程间通信，同时二进制数据保证了数据的持久化。

MessagePack 是一种数据序列化的方式，它支持多种数据类型，这里只考虑这些数据类型的一个子集:Null、Boolean、Integer、Double、String、List 和 Map。 可以认为，每种类型的数据被序列化之后，都转化成了以下形式的二进制数据:
[类型代码] [数据] 其中[类型代码]部分标识原始数据的类型，[数据]部分保存真实数据序列化之后的值。
List 和 Map 是两种特殊的数据类型，他们作为集合，可以嵌套包含任何类型的数据，它们被序列化之后[数据]部分的形式分别为:
List - [集合大小] [元素 1] [元素 2] ... [元素 N]
Map - [集合大小] [Key1] [Value1] [Key2] [Value2] ... [KeyN] [ValueN]
现在要对序列化后的数据做反序列化，这里做两点假设:
1. 被序列化之前的数据是一个 Map，所以反序列化之后我们也会得到一个 Map;
2. 所有 Map 的键值都是 String 类型。
读取序列化二进制流的代码已经实现好，接口如下:
interface Unpacker {
boolean isNull();
boolean isBoolean();
 boolean isInteger();
boolean isDouble();
boolean isString();
boolean isList();
boolean isMap();
void readNull();
boolean readBoolean();
long readInteger();
double readDouble();
String readString();
int readListSize();
int readMapSize();
}
每个 Unpacker 对象在构造的时候都包装了一份序列化数据，并在内部维护了一个指向当前消费到的数据的指针，is*方法判断指针当前指向的数据是否是某种类型，read*方法把指针当前指向的数据当成某种类型做反序列化，并把指针往前移(对于 List 和 Map 类型，读取集合的大小，紧接着需要调用其他 read*方法读取每个集合元素)。
Unpacker 已经创建好并作为参数传入，
请实现反序列化函数
Map<String, Object> unpack(Unpacker unpacker);


**静态方法和非静态方法的区别**

* 静态方法属于类，既可通过对象来调用，也可通过类来调用；非静态方法属于对象，只可以通过对象来调用。
* 静态方法和属性只能是对类而言，非静态是对于对象而言。
* 静态方法是栈分配内存，速度快，是在类第一次加载时初始化的；非静态方法是堆分配内存，速度慢，是在对象初始化时，伴随着初始化的。
* 静态变量是系统分配一个静态空间给这个变量，那么包含这个变量的类的全部实例就会共用这个变量，改变这个变量会对其余实例造成影响，编译时就被初始化；非静态变量是每个类实例都有自己的变量，任何实例的改变都不会影响到其他实例，第一次使用时才被初始化。

---

**static关键字和final关键字用法**

链接：https://blog.csdn.net/happybruce8023/article/details/79943997

作用：提高程序运行性能，优化程序结构。

static关键字：(结合源码中的例子单例来进行思考)

* 修饰成员变量：将其变成类成员，让所有对象共享属性。
* 修饰成员方法：将其变成类方法，直接使用类名.方法名的方式调用，常用于工具类。static修饰的类，成员变量和方法也必须是static声明的。
* 静态块：多个类成员放在一起初始化，使程序更加规整；static修饰的类成员，程序运行过程中，只需要初始化一次。（理解对象的初始化过程）
* 静态导包：将类方法直接导入到当前类中，直接使用“方法名”即可调用类方法，非常方便。

final关键字：(结合源码中的例子单例来进行思考)

* 修饰数据/变量：基本类型值不变，引用类型值可变。value1是常量，value2是基本类型值不变，value3是引用变量，引用变量引用的对象不变，但是值可以变。例如String没有用final修饰，所以引用还可以指向其他的对象。
* 修饰方法参数：变量在生存期中值不被改变。final值赋值一次且不被改变。
* 修饰方法：该方法无法被重写。类中所有的private方法隐式指定为final。
* 修饰类：该类无法被继承。

例子：

public static final class Builder{}：static修饰类-代表该内部类可以直接通过外部类调用；final修饰类-代表该类不应该被继承。

private static final Platform PLATFORM = findPlatform()：static修饰成员变量-代表该成员变量可以直接被类名调用；final修饰成员变量-代表该成员变量引用类型不变，但是引用类型指向的值或对象可变。

**final作用于类和方法的区别**

链接：http://www.importnew.com/7553.html

作用于方法：不能被子类重写，编译时已静态绑定，不能在运行时动态绑定。

作用于类：不能被子类继承。比如String，Integer等其他包装类就是final类。

---

**String类为什么是不可变对象**

链接：https://www.cnblogs.com/wcyBlog/p/4073725.html

不可变对象的含义是创建后的对象不可以改变。这样做的好处：

第一：只有String类是不可变对象，字符串常量池才能实现。Java中的内存分为堆内存和栈内存，JVM在处理String时，会在堆内存中开辟一块区域用来存储字符串对象，这块区域被称为字符串常量池。字符串缓冲池可以节省很多内存空间，因为不同的引用可以指向同一个String对象，拷贝对象时只需要拷贝一个内存地址即可，同时，如果String对象可变，一个变量变化，会对引用同一个对象的其它变量产生影响。

第二：String是不可变对象可以保证安全。数据库的用户名和密码都是字符串，socket编程中主机名和端口也是字符串，如果String可变，黑客修改会造成安全漏洞。

第三：String是不可变对象可以保证多线程安全。并发环境下同一个时刻同时修改String对象会造成数据不正确，所以String不可变保证在并发环境下对象可以共享。

第四：类加载器会用到String字符串，如果String可变，数据库会造成破坏。

第五：String不可变会保证hashcode的唯一性。字符串创建时，hashcode就被缓存了，不需要重新计算。所以Map中的键常常使用字符串，因为字符串的处理速度快过其它的键对象。

附：

[String源码分析](https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaSE/String源码分析.md)

代码测试-AStringClass。String是常量，值在创建以后不可以改变，可以共享。

**为什么不能经常创建new对象？**

因为对象创建即new对象在堆里，堆由程序员分配内存，经常创建对象会造成资源浪费。

### 6.继承

**一个经典例子**

代码：类A，类ClientExtends。分析也见代码。

知识点：子类继承父类方法重写，属性隐藏。注意：此处对属性还要多加了解。

按照多态的特性，子类调用父类的方法，方法执行时会动态链接到子类的实现方法上。

**单继承和多继承**

Java中单继承指一个子类只能有一个父类，用extends关键字实现。默认继承Object类。Java不支持多继承，即一个子类有很多父类，但是可以通过一些巧妙的设计达到和多继承同样的效果。接口。一个类可以实现多个接口，达到向上转型的目的，用implements关键字实现。

补充：一个外部类outer class可以有多个内部类inner class，每个内部类继承同一个外部类。

**为什么没有多继承？多继承有反射机制吗？**

Java中为什么没有多继承：

链接：https://zhidao.baidu.com/question/78590472.html

如果一个子类继承的多个父类中拥有相同名字的实例变量，子类在引用该变量时将产生歧义，无法判断应该使用哪个父类的变量；如果一个子类继承的多个父类中有相同的方法，子类在调用该方法时将产生歧义，无法判断应该调用哪个父类的方法。所以在接口中不能有实例变量和具体方法，只能有静态变量和抽象方法。静态变量在编译时决定调用关系，即使存在冲突也会在编译时报错。引用时通过类名或接口名调用，从而避免产生歧义。

Java没有多继承，所以也没有反射机制。

**抽象类和接口**

共同点：都是抽象的，即把多个事物共性的内容抽取出来，都不能实例化，都能实现多态。

区别：最简单的区别就是关键字，抽象类用extends继承，接口用implements实现；抽象类有构造函数，接口没有；接口解决了Java中只能单继承的痛点，是设计的结果，可以多实现，而抽象类是重构的结果，不能多实现，接口比抽象类抽象级别更高；抽象类中可以有实例域和具体方法，接口中不可以，接口中只有抽象方法和常量(此为本质区别)。

补充：接口是完全抽象，用于功能拓展，比抽象类更加宽泛；而抽象类类似于下定义，比如abstractList是有序的，那么它的子类也是有序的，为部分抽象，有局限。抽象类是继承，更多代表is-a关系；接口是实现，更多代表like-a关系。（联想集合）

例子：ArrayList继承AbstractList类，实现List接口。抽象类更多代表下定义，接口更多代表抽象功能。

项目中例子：

第一种：有实例域，用抽象类。

- recyclerview通用万能适配器BaseAdapter设为抽象类，抽象出每个adapter子类共性的部分。

第二种：有具体方法，用抽象类。

- 代码：BaseActivity，AActivity，BActivity。
- 代码：BaseFragment，AFragment，BFragment。

第三种：只定义抽象方法，抽象类和接口都可以。

- recyclerview多itemtype，定义一个multiItemBuilder接口或者抽象类，里面定义getLayoutId和getItemType方法。
- 子类为 Man 和 Woman，定义一个 Human 抽象类或接口，里面定义say方法。

源码中经典例子：集合。

---

**反射**

是什么：计算机程序在运行时(Run time)可以访问、检测和修改它本身状态或行为的一种能力。(百度百科：Java反射机制)

* 对于任意一个类，可以获取这个类的所有属性和方法。
* 对于任意一个对象，可以调用它的任意一个方法和属性。
* 总：动态获取信息或动态调用对象方法。
* JVM运行时才动态加载类或调用方法与属性，不需要事先(写代码时或编译期)知道运行对象是谁。

链接：https://zhuanlan.zhihu.com/p/27331449

为什么：

- 允许在编译期间不知道类名称、接口名称、字段、方法的情况下在运行时检查类、接口、字段、方法。
- 运行时动态创建和编译对象，来实现功能，体现出很大的灵活性

缺点：反射消耗资源(内存)，忽略安全性检查，破坏封装性，会对性能造成影响。(性能和便捷是一个相互博弈的过程)

怎么样：

1.获取Class类对象：

* 对象.getClass()
* Class.forName(className)。classname是类名或接口名。否则会报出一个已检查异常。
* 类名.class

附：

- 获取类名：getClass().getName()
- 获取类名(不含包名)：getClass().getSimpleName();
- 比较两个类对象：==运算符比较对象堆内存地址。代码：if（e.getClass()==Employee.class）。

应用：BaseActivity中打印SimpleClassName(类名)，显示进栈出栈的Activity。

2.获取类的构造器；

3.获取类的成员变量；

4.获取类的方法。

用途/后果：

作用：

1.利用反射分析类，调用类的属性、构造器、方法，不在运行时也可以，例如捕获异常；

2.在运行时分析类，例如访问不可变对象中的私有成员。

- 编译期(Compile Time)之外的运行期(RunTime)获得任何一个类的字节码，包括接口、变量、方法等信息。
- 在运行期实例化对象，通过调用get/set方法获取变量的值。

项目中应用：

1.心神学堂：获取状态栏和导航栏的高度(此处不用反射也可以,反射反而会影响性能)

链接：

https://blog.csdn.net/xu_coding/article/details/80387893

https://www.jianshu.com/p/c1f2a3849d6e

代码：WindowUtil

2.心神学堂：烟火控件通过反射获取Edittext光标的坐标，访问私有域，setAccessible为true。

代码：FireworkView

3.Activity界面跳转解耦(此处不用反射也可以，反射反而会影响性能)

https://blog.csdn.net/zgxzgxzg/article/details/52275071

4.解决滑动冲突也遇到了反射。(不用反射也可以)

见项目https://github.com/2211785113/Android_SlidingConflict直接搜索"反射"。

源码中应用：

1.序列化和反序列化writeObject和readObject方法中都用到了method.invoke(obj)。通过反射获取方法的method对象，然后用obj实例对象去调用方法。

链接：

https://blog.csdn.net/rickesy/article/details/56835740

https://blog.csdn.net/wenyuan65/article/details/81145900

代码：Deserialization

2.eventbus

获取订阅者中的方法，同时获取方法的修饰符和类型参数。

3.View#performClick

通过反射获取声音，播放自己想要播放的声音，重写playSound方法。

### 7.接口、lambda表达式与内部类

**lambda表达式**

**是什么：**

一个可传递的代码块，可以在以后执行一次或多次。它的重点就是延迟执行。

**为什么(演变过程)：**

链接：

https://blog.csdn.net/smileiam/article/details/73921066

https://www.cnblogs.com/etoah/p/4248684.html

lambda表达式是一种匿名函数。

演变步骤：一般的方法委托 => 匿名函数委托 => lambda表达式

委托：类似于C++里面的函数指针(指向一个方法)，并且委托约束了待指向方法的签名(由返回类型和参数组成)。

1.lambda表达式不是简单的匿名内部类，不是由匿名内部类演变而来。

因为使用匿名内部类，编译器会为每一个匿名内部类创建一个类文件，而类在使用前需要加载类文件并进行验证，这个过程会影响应用的启动性能。类文件加载可能是一个耗时的操作，若lamada采用匿名内部类实现，会使应用内存占用增加，同时也会使lamada表达式与匿名内部类的字节码生成机制绑定。所以lambda表达式不是采用匿名内部类来实现。

通过分析类经过编译之后生成的字节码，发现lambda使用了java中的动态指令，所以lambda内部并不是使用内部类来实现的。

2､lambda表达式是怎么运行的？

lambda表达式将翻译策略推迟到运行时，主要是将表达式转成字节码invoked dynamic指令，如上面编译成的字节码，主要有以下两步：

- 生成一个invoked dynamic调用点(dynamic工厂)，当lambda表达式被调用时，会返回一个lambda表达式转化成的函数式接口实例；
- 将lambda表达式的方法体转换成方法供invoked dynamic指令调用。

大多数情况下，lambda表达式比匿名内部类性能更优。

3､lambda表达式是怎么翻译成机器识别的代码？

lambda表达式翻译成实际运行代码，分为对变量捕获和不对变量捕获方法，即是否需要访问外部变量。

- 不捕获变量：方法实现会被提取到一个与之具有相同签名的静态方法中，这个静态方法和lambda表达式位于同一个类上。
- 捕获变量：依然会被提取到一个静态方法中，被捕获的变量会同正常的参数一样传入到这个方法中。

**怎么样：**

1.如何生成lamada表达式

语法：参数，箭头(->) 以及一个表达式。

2.如何把lamada表达式传递到需要一个函数式接口的方法

函数式接口：lamada表达式可看成一个函数，而不是一个对象，可以传递到函数式接口(只有一个抽象方法的接口，需要这种接口的对象时，提供一个lamada表达式)。

lamada表达式的用途：建立一个特定的函数式接口。

方法引用/构造器引用：会转换为函数式接口的实例。

变量作用域：Java语言中，lamada表达式就是闭包。可以捕获外围作用域变量中的值，但是只能引用值不会改变的变量。因为如果值可变，lamada执行多次并发操作不安全。

3.如何编写方法处理lamada表达式

```
repeat(10, () -> System.out.println("Hello, World!"));
```
要接受这个lambda表达式，需要选择(偶尔可能需要提供)一个函数式接口。在这里，我们可以使用Runnable接口。
```
public static void repeat(int n, Runnable action){
        for(int i=0; i< n; i++)
                action.run();
}
```
---

Java 8演变：(同lamada表达式)

原理：Java 7是Java文件编译成字节码class文件，再由虚拟机解释运行。Java 8是Java文件编译成字节码class文件，JVM运行时直接将字节码文件生成动态指令。(此处看虚拟机时还需要补充)

链接：https://blog.csdn.net/iamcodingmylife/article/details/90716101

---

**内部类**

是什么：定义在另一个类中的类。

- 内部类既可以访问自身的数据域， 也可以访问创建它的外围类对象的数据域。
- 内部类持有一个外部类引用，内部类对象有一个隐式引用，指向了创建它的外部类对象。这个引用在内部类的定义中不可见。
- 编译器修改了内部类的构造器，添加一个外围类引用的参数。

为什么：

* 内部类中方法可以访问该类定义所在的作用域中的数据，包括私有数据。
* 内部类可以对同一个包中的其他类隐藏起来。
* 当想要定义一个回调函数且不想编写大量代码时，使用匿名内部类比较便捷。

怎么样：

流程：常规类—>内部类—>局部内部类—>匿名内部类—>静态内部类

- 常规类：不能访问另一个外部类中的作用域。
- 内部类：既可以访问自身的作用域，也可以访问外围类的作用域；编译器为内部类生成了一个构造器，有一个隐式引用，指向了创建它的外部类中的作用域；内部类可以定义为private类，对同一个包中的其他类隐藏；匿名内部类还可以在回调的时候使用。
- 局部内部类：如果内部类只被调用一次，那么就在方法中使用局部内部类。局部类不能用public或private访问修饰符声明，它的作用域被限定在声明这个局部类的块中；可以对外部世界完全隐藏；局部类不仅能够访问外部类，还可以访问局部变量，不过局部内部类的局部变量必须设置为final。
- 匿名内部类：只创建这个类的一个对象，不必命名。没有显式的名字的内部类，隐式继承一个类或实现一个接口，是一个继承了该类或实现了该接口的子类匿名对象。实现了接口和抽象类里的方法。直接构造实现接口和抽象类的匿名内部类对象。
- 静态内部类：内部类不需要引用外部类对象时，使用静态内部类。内部类取消对外部类的引用。静态内部类可以设为公有类。静态内部类可以有静态域和静态方法。

静态内部类与普通内部类的区别：

- 静态内部类：内部类不持有外部类引用，不需要引用外部类对象。
- 普通内部类：内部类持有外部类的一个引用，会造成内存泄漏，https://www.jianshu.com/p/65f914e6a2f8。

内部类特殊语法规则：outerObject.new InnerClass

- A aaa = new A();
- B bbb = aaa.new B();

### 8.注解

**是什么：**

代码里的特殊标记即打标签。这些标记可以在编译、类加载、运行时被读取，并执行相应处理。即在不改变原有逻辑的情况下，在源文件中嵌入一些补充信息。代码分析工具、开发工具和部署工具可以通过这些补充信息进行验证、处理或部署。

Java5引入的一种代码辅助工具，它的核心作用是对类、方法、变量、参数和包进行标注，通过反射来访问这些标注信息，以此在运行时改变所注解对象的行为。

注解的本质：接口，都实现了Annotation接口。

**为什么/解决的问题：**

解耦合。

**怎么样：**

- 分类：标准注解和元注解。
- 自定义注解：基本定义，定义成员变量，定义运行时注解，定义编译时注解。
- 编写注解处理器：运行时注解采用反射机制处理注解，如retrofit的get方法；编译时注解采用AbstractProcessor处理注解，如butterknife框架。（注意思考注解的作用，为什么这里要用注解）

**用途/后果：**

注解在Android中的运用：

- 依赖注入开源框架：ButterKnife(可以用谷歌提供的DataBinding框架来替代)/Dagger2(标准注解/编译时注解)，
- 使用注解的开源框架：Retrofit/GreenDao/Gson/Eventbus/模块间组件间跳转。

### 9.异常

**异常分类**

- 非受查异常：Error、RuntimeException。(错误和运行时异常，例如类型转换，数组越界，空指针等。)
- 受查异常/已检查异常：其他异常包括IOException等。受查异常需要抛出异常声明异常，捕获异常创建异常类。

**IllegalArgumentException：非法参数异常。**
```
if (str == null) {
            throw new IllegalArgumentException("Input string should not be null");
        }
```

**try catch finally，try里有return，finally还执行么？**

会执行，在方法返回调用者前执行。如果存在finally代码块，try中的return语句不会立马返回调用者，而是纪录下返回值待finally代码块执行完毕之后再向调用者返回其值，然后如果在finally中修改了返回值，这会对程序造成很大的困扰，C#中从语法规定也不能做这样的事。

### 10.泛型

**是什么**

泛型本质：参数化类型。在不创建新类型的情况下，通过泛型指定的不同类型来控制形参具体限制的类型。

**为什么**

不同类型的对象执行相同的代码，编写的代码可以被不同类型的对象所重用。

类型参数使程序具有更好的可读性和安全性。

* 编译器调用get不用进行强制类型转换，就知道返回值类型；
* 编译器调用add知道参数类型，可进行检查，避免插入错误类型的对象。

**怎么样**

1.泛型类/泛型方法/泛型接口/类型变量限定

- 泛型类：具有一个或多个类型变量(T)的类。普通方法中使用泛型。
- 泛型方法：完全独立，可以定义在普通类，泛型类中。调用时指明具体类型。public static <T> T getMiddle(T... a){};泛型类中定义了泛型方法，类型可以不一样。
- 泛型接口：实现泛型接口的类：未传入泛型实参：class A<T> implements interface<T>{}，new的时候传入具体类型。传入泛型实参：class A implements interface<String>{}。
- 泛型类/泛型方法-类型变量限定：限定类型用“&”分隔；类型变量用“，”分隔。T extends Comparable & Serializable。有多个限定类型时，类放在接口前面。
- 通配符类型：允许类型参数变化；超类super，子类extends；无限定通配符？。
- 反射：运行时获得泛型类的信息。

2.泛型代码和虚拟机

- 泛型代码和虚拟机：因为虚拟机中没有泛型，所以虚拟机会进行类型擦除，翻译泛型表达式。
- 虚拟机中没有泛型，只有普通的类和方法。
- 所有的类型参数都用限定类型转换。
- 桥方法被合成来支持多态。
- 为保持类型安全性，必要时插入强制类型转换。

3.泛型的缺陷

- 约束和局限性：因为强制类型转换，所以不能用基本类型，需要用包装类型；不能用instanceof操作符和强制类型转换；不能用泛型数组；不能实例化。
- 泛型的继承规则：无论S和T有什么联系，通常，Pair< S >与Pair< T >没有什么联系。

**用途/后果**

项目中使用：

泛型类：

- 请求网络Gson返回数据：Result<T>，里边有code，msg，data三个域。
- mvp模式：BasePresenter<V extends BaseView>
- RecyclerView基类adapter：BaseRecvAdapter<T>：[项目地址](https://github.com/2211785113/BaseRecvAdapter)

泛型方法：

- RecyclerView的adapter：BaseViewHolder#getView方法：对布局中控件id的获取，return view。：[项目地址](https://github.com/2211785113/BaseRecvAdapter)

例子：

- 泛型类：本项目Genericity
- 客户端：本项目ArrayAlg

### 11.集合

**常用集合框架**

- Collection：List，Set，Queue/Deque。
- Map

说明：LinkedList类既实现了Deque接口，也实现了Collection接口。

---

**常用集合框架实现原理**

参考链接：http://wiki.jikexueyuan.com/project/java-collection/hashmap.html

源码剖析：https://github.com/2211785113/Blog/blob/master/Java基础/Java集合

---

**ArrayList与LinkedList的区别**

ArrayList：

* 基于数组实现；
* 整数索引访问，随机访问，访问元素很方便；
* 但是数组在连续的位置上存放对象引用，插入删除要挪动大量元素。

LinkedList：

* 基于双向链表实现；
* 顺序访问，迭代器访问，要移动指针，访问不方便，需要一直遍历；但是有一种情况特殊，列表反转Collections.reverse();LinkedList比ArrayList快，ArrayList需要挪动大量的元素，但是LinkedList只需要将指针重新指向即可。
* 但是插入删除比较方便，链表将每个对象存放在独立的结点中，每个结点还存放着序列中下一个结点的引用。

---

**LinkedHashMap与LRUcache**

链接：http://wiki.jikexueyuan.com/project/java-collection/linkedhashmap-lrucache.html

**HashMap解决散列冲突的方法**

链接：https://www.cnblogs.com/peizhe123/p/5790252.html。

**HashTable与HashMap的区别**

- 共同点：作用一样，拥有相同的接口，但是HashTable现在不用。
- HashMap：线程不安全
- HashTable：线程安全

---

**HashSet与HashMap比较**

链接：http://wiki.jikexueyuan.com/project/java-collection/hashset-and-hashmap.html

**用途/后果**

项目中应用-心神学堂：

- List：LinkedList：add/remove；ArrayList：get，add(数量少)
- Set：HashSet：图片没有重复对象
- Map：HashMap：不要求同步；ConcurrentHashMap：并发访问，线程安全；LinkedHashMap：记住插入元素项的顺序

### 12.并发

**线程中sleep方法和wait方法的区别**

链接：https://www.cnblogs.com/linkstar/p/6043846.html

- sleep：线程类Thread的静态方法。调用该方法使线程暂停执行指定的时间，将CPU让给其他线程，并不释放所持有的对象锁，休眠时间结束后线程回到就绪状态。sleep使线程睡眠，让出cpu，结束后自动继续执行。
- wait：Object类的方法。调用wait方法，线程释放所持有的对象锁，进入等待池中，只有调用notify方法（或notifyAll方法），才能唤醒等待池中的线程进入等锁池，若线程获得对象的锁，则线程重新进入就绪状态。wait是对象锁，锁定方法不让继续执行，当执行notify方法后就会继续执行。

**通过wait的重载方法，实现一个线程睡眠的方法**
  static void sleep(long sleepTime) throws InterruptedException{
          synchronized(this){
                 Thread.currentThread().wait(sleepTime);
          }
  }

---

**Java中实现线程间相互通信访问数据的方法**

* 通过synchronized关键字以“上锁”机制实现线程间通信。多个线程持有同一个对象，他们可以访问同一个共享变量，利用synchronized“上锁”机制，哪个线程拿到了锁，它就可以对共享变量进行修改，从而实现通信。
* 使用Object类的wait/notify机制，执行代码obj.wait();后这个对象obj所在的线程进入阻塞状态，直到其他线程调用了obj.notify();方法后线程才会被唤醒。

**Java多线程的几种实现方式**

链接：https://www.cnblogs.com/felixzh/p/6036074.html

**Java中的线程同步有哪几种方式，举例说明**

链接：https://blog.csdn.net/QQQQQQ654/article/details/70144050

**Java的多线程机制**

https://www.cnblogs.com/keyi/p/7173036.html