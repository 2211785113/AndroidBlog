package com.example.ruru.sophiesblog.java.genericity;

/**
 * 泛型类
 */
public class ArrayAlg<T> {

    private T max;
    private T min;

    public ArrayAlg(T max, T min) {
        this.max = max;
        this.min = min;
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
