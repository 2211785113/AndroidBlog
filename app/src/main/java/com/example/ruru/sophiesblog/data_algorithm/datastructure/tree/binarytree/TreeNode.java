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

    /**
     * 创建二叉树
     *
     * @return
     */
    public static TreeNode createTree() {
        TreeNode d = new TreeNode("D");
        TreeNode e = new TreeNode("E");
        TreeNode f = new TreeNode("F");
        TreeNode g = new TreeNode("G");

        TreeNode b = new TreeNode("B", d, e);
        TreeNode c = new TreeNode("C", f, g);

        TreeNode a = new TreeNode("A", b, c);

        return a;
    }

    /**
     * 数组转化成二叉树
     * [1,2,3,4]
     * [1,2,3,null,4]
     *
     * @param arr
     * @return
     */
    public static TreeNode strToTree(String[] arr) {
        int len = arr.length;

        List<TreeNode> list = new ArrayList<TreeNode>();
        for (int i = 0; i < len; i++) {
            TreeNode node = new TreeNode(arr[i]);
            list.add(node);
        }

        if (list.size() > 0) {
            //第1行到第n-1行
            for (int i = 0; i < len / 2 - 1; i++) {
                if (list.get(2 * i + 1) != null) {
                    list.get(i).left = list.get(2 * i + 1);
                }
                if (list.get(2 * i + 2) != null) {
                    list.get(i).right = list.get(2 * i + 2);
                }
            }
            // 判断最后一个根结点：因为最后一个根结点可能没有右结点，所以单独拿出来处理
            int lastIndex = len / 2 - 1;
            list.get(lastIndex).left = list.get(2 * lastIndex + 1);
            // 右结点，如果数组的长度为奇数才有右结点
            if (len % 2 == 1) {
                list.get(lastIndex).right = list.get(2 * lastIndex + 2);
            }
        }

        return list.get(0);
    }
}
