## LinkedList源码剖析

### 介绍

1.LinkedList 不是线程安全的，只能在单线程环境下使用。多线程环境下可以考虑用collections.synchronizedList(new LinkedList())函数返回一个线程安全的LinkedList类。

2.LinkedList 实现了List接口，能增add，删remove，改set，查get，遍历iterator元素；实现了Deque接口，有双端队列的功能；实现了Serializable接口，因此它支持序列化，能够通过序列化传输；实现了Cloneable接口，能被克隆。

3.LinkedList 在JDK 1.7以前是双向循环链表，1.7及以后是双向链表/双端队列。有一个头部节点和尾部节点的引用，first和last。

### 源码分析见注释，代码见LinkedList类

```
/**
 * 链表节点为node
 */
public class LinkedList<E>
        extends AbstractSequentialList<E>
        implements List<E>, Deque<E>, Cloneable, java.io.Serializable {

    private static final long serialVersionUID = 876323262645176354L;

    //长度
    transient int size = 0;

    //第一个节点的引用
    transient Node<E> first;

    //最后一个节点的引用
    transient Node<E> last;

    //构造方法：无参
    public LinkedList() {
    }

    //构造方法：带有集合参数
    public LinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    /**
     * 增：插入元素到list集合的末尾
     */
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    /**
     * 增：插入元素在list的头部位置
     */
    public void addFirst(E e) {
        linkFirst(e);
    }

    /**
     * 增：插入元素在list的尾部位置
     */
    public void addLast(E e) {
        linkLast(e);
    }

    /**
     * 增：插入元素在list的头部位置。画图：很简单。(重点)
     */
    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;//插入元素size+1
        modCount++;
    }

    /**
     * 增：插入元素在list的尾部位置。画图：很简单。（重点）
     */
    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }

    /**
     * 增：插入元素在指定的节点位置：
     * 注意：pred节点，succ节点，newNode节点都需要互相指向新的左右节点
     * 注意：判断pred为null问题
     */
    void linkBefore(E e, Node<E> succ) {
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
        modCount++;
    }

    /**
     * 增：c集合所有元素到list集合的末尾，返回正确的集合遍历器。注意：list集合非空时，在添加操作进行中，修改不太好。参数：从size索引开始添加元素
     */
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    /**
     * 增：插入c集合的所有元素到list集合中，开始在指定的位置index，最后返回正确的集合遍历器。画图：很简单（重点）
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        checkPositionIndex(index);

        //插入集合转为数组，如果插入集合为空，那就不添加了
        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;

        /**
         * 数组长度不为0时执行以下操作：
         * 定义左右两个节点：pred-左边；succ-右边
         * 这段代码的操作：负责给即将插入集合的前边一个节点和后边一个节点赋值
         */
        Node<E> pred, succ;
        if (index == size) {
            succ = null;
            pred = last;
        } else {
            succ = node(index);
            pred = succ.prev;
        }

        //给c集合元素定义节点，并且左右节点赋值
        for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            Node<E> newNode = new Node<>(pred, e, null);
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            pred = newNode;
        }

        if (succ == null) {
            last = pred;
        } else {
            pred.next = succ;
            succ.prev = pred;
        }

        size += numNew;
        modCount++;
        return true;
    }

    /**
     * 增：插入元素在指定的索引位置index
     */
    public void add(int index, E element) {
        checkPositionIndex(index);

        if (index == size)
            linkLast(element);
        else
            linkBefore(element, node(index));
    }

    /**
     * 删：移除并返回list第一个节点
     */
    public E removeFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    /**
     * 删：移除并返回list最后一个节点
     */
    public E removeLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }

    /**
     * 删：移除第一个非空节点
     */
    private E unlinkFirst(Node<E> f) {
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        modCount++;
        return element;
    }

    /**
     * 删：移除最后一个非空节点
     */
    private E unlinkLast(Node<E> l) {
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        modCount++;
        return element;
    }

    /**
     * 删：移除非空节点
     */
    E unlink(Node<E> x) {
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        modCount++;
        return element;
    }

    /**
     * 删：移除list集合中第一次出现的该元素
     */
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 删：移除list集合中所有元素
     */
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
        modCount++;
    }

    /**
     * 删：删除索引index处的节点的元素
     */
    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    /**
     * 改：更新索引index处的节点的值
     */
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    /**
     * 查：返回第一个节点的值
     */
    public E getFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }

    /**
     * 查：返回最后一个节点的值
     */
    public E getLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }

    /**
     * 查：list包含此元素返回true
     */
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * 查：获取索引index处的节点的值
     */
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    /**
     * 查：getIndexNode，获取index索引处的节点
     */
    Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    /**
     * 查：返回list集合中第一次出现该元素的索引
     */
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    /**
     * 查：返回list集合中最后一次出现该元素的索引
     */
    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (x.item == null)
                    return index;
            }
        } else {
            for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (o.equals(x.item))
                    return index;
            }
        }
        return -1;
    }

    /**
     * 长度：返回list集合的长度
     */
    public int size() {
        return size;
    }

    /**
     * 判断
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 判断
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 判断：index在集合中是否存在
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * 判断：添加元素的index在一个有效的位置
     */
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * 异常：越界异常输出信息
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    // ================================================================================队列操作

    /**
     * 增：添加元素在list集合的尾部
     */
    public boolean offer(E e) {
        return add(e);
    }

    /**
     * 删：取出并移除list集合的头部元素
     */
    public E poll() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    /**
     * 删：取出并移除list集合的头部元素
     */
    public E remove() {
        return removeFirst();
    }

    /**
     * 查：取出list集合的头部元素
     */
    public E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    /**
     * 查：取出list集合的头部元素
     */
    public E element() {
        return getFirst();
    }

    // ============================================================================双端队列操作

    /**
     * 增：插入元素在list的头部位置
     */
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    /**
     * 增：队列-插入元素在list集合的头部
     */
    public void push(E e) {
        addFirst(e);
    }

    /**
     * 增：插入元素在list的尾部位置
     */
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    /**
     * 删：堆栈-移除并返回list头部元素
     */
    public E pop() {
        return removeFirst();
    }

    /**
     * 删：移除list集合中第一次出现的该元素
     */
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    /**
     * 删：移除list集合中最后一次出现的该元素
     */
    public boolean removeLastOccurrence(Object o) {
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = last; x != null; x = x.prev) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 删：取出并移除list集合的头部元素
     */
    public E pollFirst() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    /**
     * 删：取出并移除list集合的尾部元素
     */
    public E pollLast() {
        final Node<E> l = last;
        return (l == null) ? null : unlinkLast(l);
    }

    /**
     * 查：取出list集合的头部元素
     */
    public E peekFirst() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    /**
     * 查：取出list集合的尾部元素
     */
    public E peekLast() {
        final Node<E> l = last;
        return (l == null) ? null : l.item;
    }

    // ============================================================================Node类

    /**
     * 类：节点
     * item：数据域
     * next：下一个
     * prev：上一个
     */
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    // ============================================================================克隆

    @SuppressWarnings("unchecked")
    private LinkedList<E> superClone() {
        try {
            return (LinkedList<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    /**
     * 浅拷贝
     */
    public Object clone() {
        LinkedList<E> clone = superClone();

        clone.first = clone.last = null;
        clone.size = 0;
        clone.modCount = 0;

        // Initialize clone with our elements
        for (Node<E> x = first; x != null; x = x.next)
            clone.add(x.item);

        return clone;
    }

    // ============================================================================集合转数组

    /**
     * 集合转数组
     */
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    /**
     * 集合转数组
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            a = (T[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        int i = 0;
        Object[] result = a;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;

        if (a.length > size)
            a[size] = null;

        return a;
    }

    // ============================================================================序列化和反序列化

    /**
     * 序列化：对象转字节流
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        // Write out any hidden serialization magic
        s.defaultWriteObject();

        // Write out size
        s.writeInt(size);

        // Write out all elements in the proper order.
        for (Node<E> x = first; x != null; x = x.next)
            s.writeObject(x.item);
    }

    /**
     * 反序列化：字节流转化为对象
     */
    @SuppressWarnings("unchecked")
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();

        //读取长度
        int size = s.readInt();

        //读取元素
        for (int i = 0; i < size; i++)
            linkLast((E) s.readObject());
    }

```

### 综合源码来看：

1.LinkedList类里定义了一个Node类，有item,prev,next属性;定义了两个节点属性，first和last。

2.LinkedList里边元素允许为null。

3.LinkedList可以实现增删改查，集合转数组，序列化反序列化，克隆功能。

4.进行增删改查操作：都要对Node节点的item,prev,next进行指定。

5.注意node方法，获取index索引处的节点。此处用了二分查找，以size>>1为中间值。

6.增删查改操作：注意边界条件。

【科普】transient关键字：不参与序列化，不持久化，不保存这个值。


