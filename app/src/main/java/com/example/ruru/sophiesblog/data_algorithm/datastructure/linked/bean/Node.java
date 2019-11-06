package com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.bean;

/**
 * 单链表：泛型类-E/T
 */
public class Node<E> {
    public E e;
    public Node next;
//    Node prev;

    public Node(E e, Node next) {
        this.e = e;
        this.next = next;
    }
}
