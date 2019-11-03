package com.example.ruru.sophiesblog.java.collection.list;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;

/**
 * 大致等同于Vector，除了ArrayList不是同步的，而Vector是同步的。
 * 容量：用来存储list元素的数组的大小。增加add元素时，容量自动增长。
 */
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

    private static final long serialVersionUID = 8683452581122892189L;

    //默认容量
    private static final int DEFAULT_CAPACITY = 10;

    //空数组实例
    private static final Object[] EMPTY_ELEMENTDATA = {};

    //空数组
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    //存储元素的数组
    transient Object[] elementData;//transient关键字看末尾科普

    //长度
    private int size;

    /**
     * 构造方法：初始化数组容量大小
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
     * 构造方法：无参，设置为空数组。
     */
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 构造方法：参数为一个集合
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

    // ============================================================================容量

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

    // ============================================================================增删改查

    /**
     * 增：添加元素到list集合的末尾
     */
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = e;
        return true;
    }

    /**
     * 增：添加元素到list集合的特定索引，其它元素往后挪
     */
    public void add(int index, E element) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));

        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);//这块处理的非常巧妙
        elementData[index] = element;
        size++;
    }

    /**
     * 增：追加集合c到list集合的末尾
     */
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * 增：添加集合c到list集合，新添加的元素开始索引为index
     */
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

    /**
     * 删：移除第一次出现的元素
     */
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

    /**
     * 删：移除元素：跳过了索引检查和没有返回值。
     */
    private void fastRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        elementData[--size] = null; //让GC回收
    }

    /**
     * 删：移除list集合特定位置的元素，其它元素向左移
     */
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

    /**
     * 删：移除list集合从fromIndex索引到toIndex索引的元素
     */
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

    /**
     * 删：移除list集合所有包含在c集合中的的元素，结果list中的元素不包含c
     */
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return batchRemove(c, false);
    }

    /**
     * 删：仅仅保留list集合中的包含在c中的元素，即移除不在c集合中的元素。结果list中的元素包含c
     */
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return batchRemove(c, true);
    }

    /**
     * 删：complement 为true，移除不在c集合中的元素，留下在c集合中的元素；为false，移除在c集合中的元素，留下不在c集合中的元素。
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
     * 删：清空list集合所有元素，集合变为null
     */
    public void clear() {
        modCount++;

        for (int i = 0; i < size; i++)
            elementData[i] = null;//让GC回收不再存活的对象

        size = 0;
    }

    /**
     * 改：替代list集合特定位置的元素
     */
    public E set(int index, E element) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));

        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    /**
     * 查：返回最后一个出现要查找元素的索引，否则返回-1
     */
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


    /**
     * 查：返回list特定索引的元素
     */
    public E get(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));//index索引大于list集合的长度，抛出越界异常

        return (E) elementData[index];//数组检索
    }

    /**
     * 查：返回第一个出现要查找元素的索引，否则返回-1
     */
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

    /**
     * 查：如果list包含元素返回true
     */
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }


    /**
     * 长度：list元素的大小
     */
    public int size() {
        return size;
    }

    /**
     * 判断：list中是否有元素
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 异常：抛出异常打印越界信息：索引index和大小size
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }


    // ============================================================================集合转数组

    /**
     * 集合转数组：转为Object类型数组。Arrays.copyOf：为数组分配一个新的内存，调用者可以去修改。
     */
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * 集合转数组：转为T类型数组。泛型方法。如果新数组长度<list集合的长度，长度补足为size；如果新数组长度>list集合的长度，用null补足。
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

    // ============================================================================克隆

    /**
     * 浅拷贝arraylist实例
     */
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

    // ============================================================================序列化与反序列化

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
