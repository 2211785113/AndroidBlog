## LinkedList源码剖析

### 介绍

LinkedList不是线程安全的，只能在单线程环境下使用。多线程环境下可以考虑用collections.synchronizedList(List l)函数返回一个线程安全的LinkedList类。

LinkedList实现了List接口，能添加add，修改set，删除remove，访问get，遍历iterator元素；实现了Deque接口，有双端队列的功能；实现了Serializable接口，因此它支持序列化，能够通过序列化传输；实现了Cloneable接口，能被克隆。

ArrayList是基于双向循环列表实现的。

### 源码分析见注释，代码见LinkedList类

```
```