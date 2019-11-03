## HashMap源码剖析

### 介绍

1.HashTable 和 HashMap 都实现了Map接口。但是HashTable是同步的，HashMap是不同步的。

2.HashMap 两种操作：get和put操作，容量不够可以自动增长。容量是由初始容量和负载因子决定的，容量不够进行reHash。

3.HashMap 线程问题：HashMap不是线程安全的，并发环境下，可能会引起死循环即死锁。多线程环境下可以考虑用Map m = Collections.synchronizedMap(new HashMap(...))函数返回一个线程安全的HashMap类。

4.演变进化：HashMap桶数量足够大时，会转化成TreeNode节点，即TreeMap。

5.HashMap是一种基于哈希表实现的Map，它通过键的hashCode来快速的存取元素

6.HashMap允许插入null键和null值，允许多条记录的值为null，但只允许一条记录的键为null

7.HashMap中的元素是无序的，无法保证遍历时的顺序是固定不变的

8.HashMap在不考虑哈希碰撞的情况下，插入和查询的时间复杂度可以达到O(1)

### 源码分析见注释，代码见HashMap类

### 从源码中可以看出：


### 小结

1.HashMap的实现原理：HashMap是一个用于存储Key-Value键值对的集合，每一个键值对也叫做Entry。这些键值对即Entry分散存储在一个数组当中，这个数组就是HashMap的主干。当进行put操作的时候，把输入的key值，根据哈希函数确定Entry的插入位置index，因为HashMap长度有限，所以同一个index用链表来存放Entry，进行遍历更新值。当进行get操作的时候，把输入的key做一次hash映射，获得对应的index，然后遍历链表得到对应key的value值。

2.HashMap的初始化长度为16，或者2的幂次方。因为会根据key的hash值位运算与(length-1)获取到index，(length-1)的二进制位数都是1，最后得到的index值会更加平均，hash算法也更加平均。如果初始化长度为10的话，有些index结果的出现几率更大，有些结果永远不会出现，不符合hash算法均匀分布的原则。

3.HashMap高并发情况下为什么会出现死锁：因为HashMap的容量大于初始容量*负载因子的值时，就会扩充容量，执行rehash操作。



