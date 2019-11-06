package com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 每个小二叉树的节点
 * <p>
 * 二叉树知识点：
 * 每行最后一个数：等比数列求和：S(n)=a1(1-q^n)/(1-q)
 * 最后一行：S(n-1)=2^n-1
 * 倒数第二行：S(n-2)=2^(n-1)-1
 * <p>
 * 所以：
 * S(n-2)=S(n-1)/2+1=len/2+1
 * S(n-2)=(S(n-1)-1)/2=(len-1)/2
 */
public class TreeNode<T> {
    public T value;
    public TreeNode left, right;
    public TreeNode next;

    public TreeNode(T value) {
        this.value = value;
    }

    public TreeNode(T value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
