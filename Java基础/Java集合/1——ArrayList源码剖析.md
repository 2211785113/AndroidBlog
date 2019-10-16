## ArrayList源码剖析

### 介绍

ArrayList不是线程安全的，只能在单线程环境下，多线程环境下可以考虑用collections.synchronizedList(List l)函数返回一个线程安全的ArrayList类，也可以使用concurrent并发包下的CopyOnWriteArrayList类。ArrayList大致等同于Vector，除了Vector是同步的，ArrayList不是。

ArrayList实现了List接口，能添加add，修改set，删除remove，访问get，遍历iterator元素；实现了Serializable接口，因此它支持序列化，能够通过序列化传输；实现了RandomAccess接口，支持快速随机访问，实际上就是通过下标序号进行快速访问；实现了Cloneable接口，能被克隆。

ArrayList是基于数组实现的，是一个动态数组，有一个容量用来存储list元素的数组的大小，增加add或addAll元素时容量会自动增长。类似于C语言中的动态申请内存，动态增长内存。

### 源码分析见注释，代码见ArrayList类

```
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

    //序列号
    private static final long serialVersionUID = 8683452581122892189L;

    //默认容量
    private static final int DEFAULT_CAPACITY = 10;

    //空数组实例
    private static final Object[] EMPTY_ELEMENTDATA = {};

    //空数组
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    //存储list集合元素的数组
    transient Object[] elementData;

    //list集合中包含元素的大小
    private int size;

    /**
     * 初始化ArrayList
     *
     * @param initialCapacity 数组容量大小
     */
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            //容量大于0，则新建一个容量为initialCapacity的数组
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            //容量为0，数组赋值为空数组{}
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            //参数不对，抛出非法参数异常
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    /**
     * 初始化ArrayList
     * <p>
     * 无参构造方法，设置为{}空数组。
     */
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 初始化ArrayList
     *
     * @param c 参数为一个集合
     */
    public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();//集合转为数组
        if ((size = elementData.length) != 0) {
            if (elementData.getClass() != Object[].class)//如果数组类型不是Object[]
                elementData = Arrays.copyOf(elementData, size, Object[].class);//复制数组，将数组转为Object类型
        } else {
            //集合长度为0，赋值为空数组。
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    //将list集合的容量纠正为正确的size大小
    public void trimToSize() {
        //修改次数
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0)
                    ? EMPTY_ELEMENTDATA
                    : Arrays.copyOf(elementData, size);
        }
    }

    //保证容量：增加list集合的容量，必要的话，保证最小的容量即minCapacity
    public void ensureCapacity(int minCapacity) {
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                ? 0//不为空保证0个容量
                : DEFAULT_CAPACITY;//为空保证10个容量

        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

    //保证容量
    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    //保证容量，增加list集合的容量
    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        if (minCapacity - elementData.length > 0)//增加元素时minCapacity=size+1
            grow(minCapacity);
    }

    //分配给数组的最大大小
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    //增加容量
    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);//新容量=3/2的旧容量
        if (newCapacity - minCapacity < 0)//新容量还是小于需要的容量
            newCapacity = minCapacity;//新容量=需要的容量
        if (newCapacity - MAX_ARRAY_SIZE > 0)//新容量大于应用最大容量
            newCapacity = hugeCapacity(minCapacity);//抛出内存不足异常
        elementData = Arrays.copyOf(elementData, newCapacity);//复制一个数组，增加容量完成
    }

    //最大容量
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0)
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    //list元素的大小
    public int size() {
        return size;
    }

    //判断list中是否有元素
    public boolean isEmpty() {
        return size == 0;
    }

    //如果list包含元素返回true
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    //返回第一个出现要查找元素的索引，否则返回-1
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    //返回最后一个出现要查找元素的索引，否则返回-1
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = size - 1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    //浅拷贝arraylist实例
    public Object clone() {
        try {
            ArrayList<?> v = (ArrayList<?>) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            //应该不会出现异常，因为实现了Cloneable接口
            throw new InternalError(e);
        }
    }

    /**
     * list集合转为Object类型数组
     * Arrays.copyOf：为数组分配一个新的内存，调用者可以去修改。
     */
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * list集合转为T类型数组
     * 泛型方法
     * 如果新数组长度<list集合的长度，长度补足为size
     * 如果新数组长度>list集合的长度，用null补足。
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    //返回list特定索引的元素
    public E get(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));//index索引大于list集合的长度，抛出越界异常

        return (E) elementData[index];//数组检索
    }

    //替代list集合特定位置的元素
    public E set(int index, E element) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));

        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    //添加元素到list集合的末尾
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = e;
        return true;
    }

    //添加元素到list集合的特定索引，其它元素往后挪
    public void add(int index, E element) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));

        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);//这块处理的非常巧妙
        elementData[index] = element;
        size++;
    }

    //移除list集合特定位置的元素，其它元素向左移
    public E remove(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));

        modCount++;
        E oldValue = (E) elementData[index];

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        elementData[--size] = null; // 对象为空，不再存活，GC会回收。

        return oldValue;
    }

    //移除第一次出现的元素
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    //移除元素：跳过了索引检查和没有返回值。
    private void fastRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        elementData[--size] = null; //让GC回收
    }

    //清空list集合所有元素，集合变为null
    public void clear() {
        modCount++;

        for (int i = 0; i < size; i++)
            elementData[i] = null;//让GC回收不再存活的对象

        size = 0;
    }

    //追加集合c到list集合的末尾
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    //添加集合c到list集合，新添加的元素开始索引为index
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount

        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index, elementData, index + numNew,
                    numMoved);

        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    //移除list集合从fromIndex索引到toIndex索引的元素
    protected void removeRange(int fromIndex, int toIndex) {
        if (toIndex < fromIndex) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }

        modCount++;
        int numMoved = size - toIndex;
        System.arraycopy(elementData, toIndex, elementData, fromIndex,
                numMoved);

        int newSize = size - (toIndex - fromIndex);
        for (int i = newSize; i < size; i++) {
            elementData[i] = null;
        }
        size = newSize;
    }

    //抛出异常打印越界信息：索引index和大小size
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    //移除list集合所有包含在c集合中的的元素，结果list中的元素不包含c
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return batchRemove(c, false);
    }

    //仅仅保留list集合中的包含在c中的元素，即移除不在c集合中的元素。结果list中的元素包含c
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return batchRemove(c, true);
    }

    /**
     * @param c
     * @param complement 为true，移除不在c集合中的元素，留下在c集合中的元素；
     *                   为false，移除在c集合中的元素，留下不在c集合中的元素。
     * @return
     */
    private boolean batchRemove(Collection<?> c, boolean complement) {
        final Object[] elementData = this.elementData;
        int r = 0, w = 0;
        boolean modified = false;
        try {
            for (; r < size; r++)
                //此处处理非常巧妙
                if (c.contains(elementData[r]) == complement)
                    elementData[w++] = elementData[r];//结果r和w都等于size
        } finally {
            if (r != size) {
                System.arraycopy(elementData, r,
                        elementData, w,
                        size - r);
                w += size - r;
            }
            if (w != size) {
                for (int i = w; i < size; i++)
                    elementData[i] = null;
                modCount += size - w;
                size = w;
                modified = true;
            }
        }
        return modified;
    }

    /**
     * 对象的序列化：Java对象转换成字节序列的过程，对象输出流调用writeObject(object)方法把字节序列存到文件中(写入文件)
     * 将ArrayList的“容量，所有的元素值”都写入到输出流中
     *
     * @param s 对象输出流
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        int expectedModCount = modCount;
        s.defaultWriteObject();

        // 写入"数组的容量"
        s.writeInt(size);

        // 以正确的顺序写入数组的元素
        for (int i = 0; i < size; i++) {
            s.writeObject(elementData[i]);
        }

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * 对象的反序列化：字节序列转换成Java对象的过程，对象输入流通过readObject方法把字节序列存储为对象（读取文件）
     * 先将ArrayList的“容量”读出，然后将“所有的元素值”读出
     *
     * @param s 对象输入流
     */
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        elementData = EMPTY_ELEMENTDATA;

        // Read in size, and any hidden stuff
        s.defaultReadObject();

        //从输入流中读取ArrayList的“容量”
        s.readInt();

        if (size > 0) {
            //类似于clone对象，分配内存
            ensureCapacityInternal(size);

            Object[] a = elementData;
            // 从输入流中将“所有的元素值”读出
            for (int i = 0; i < size; i++) {
                a[i] = s.readObject();
            }
        }
    }
}

```

从以上代码可以看出：

1.ArrayList有三个构造方法。无参构造方法构造的ArrayList的容量默认为10，带有指定容量参数的构造方法，带有Collection参数并转化为数组赋给ArrayList的实现数组elementData的构造方法。

2.扩充容量的方法ensureCapacity：每次增加元素都要调用该方法确保足够的容量，当容量不够的时候，扩充新容量为旧容量的3/2，如果还是不够，新容量赋值为所需要的容量；最后调用Arrays.copyOf数组来达到增加容量的目的。此处会降低性能。所以增加元素不建议使用ArrayList。

3.ArrayList有两个方法可以转为数组，一个是toArray()，一个是toArray(T[] t)。第二个方法数组可向下转型，调用Arrays.copyOf方法分配一个内存新建一个T数组。

4.源码大量调用了Arrays的copyOf方法和System的arraycopy方法。这两个方法究竟做了什么操作，下面我们来进行分析：

Arrays.copyOf：

源码如下：
```
/**
     * 参数：原来数组，返回数组长度，返回数组的类型
     * U：原来数组  T：返回数组
     * 此方法为数组分配一个新的内存
     */
    public static <T, U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
        //负责转化数组的类型：如果newType是object类型的数组，直接返回object类型数组；如果不是，则转化为T类型的数组。
        @SuppressWarnings("unchecked")
        T[] copy = ((Object) newType == (Object) Object[].class)
                ? (T[]) new Object[newLength]
                : (T[]) Array.newInstance(newType.getComponentType(), newLength);
        //复制数组：original到copy数组，原来数组和目标数组索引都是从0开始，长度为旧数组长度和新长度的最小值
        System.arraycopy(original, 0, copy, 0,
                Math.min(original.length, newLength));
        return copy;
    }
```

System.arraycopy：

copy原来数组src从srcPos索引位置开始，到数组dest从destPos索引位置开始，copy数组长度为length。

源码如下：
```
public static native void arraycopy(Object src,  int  srcPos,
                                        Object dest, int destPos,
                                        int length);
```

5.ArrayList基于数组实现，可以通过下标索引直接查找到指定位置的元素，因此查找效率高，但每次插入或删除元素，就要大量地移动元素，且要分配一个新内存，所以插入删除元素的效率低。

6.在查找给定元素索引值等的方法中，源码都将该元素的值分为null和不为null两种情况处理，ArrayList中允许元素为null。