package com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.java.collection.list.ArrayList;

import java.util.List;

public class CreateTree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tree);

        TreeNode tree1 = createTree();
        Log.d(getClass().getName(), "onCreate:res1= " + tree1);

        String[] str = {"A", "B", "C", "D", "E", "F", "G"};

        TreeNode tree2 = strToTree(str);
        Log.d(getClass().getName(), "onCreate:res2= " + tree2);

        TreeNode tree3 = strToTree1(str);
        Log.d(getClass().getName(), "onCreate:res3= " + tree3);
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

    /**
     * 不对，还是要像strToTree判断最后一个结点
     */
    public static TreeNode strToTree1(String[] arr) {
        int len = arr.length;

        List<TreeNode> list = new ArrayList<TreeNode>();
        for (int i = 0; i < len; i++) {
            TreeNode node = new TreeNode(arr[i]);
            list.add(node);
        }

        if (list.size() > 0) {
            //第1行到第h-1行
            for (int i = 0; i < len / 2 - 1; i++) {
                if (list.get(2 * i + 1) != null) {
                    list.get(i).left = list.get(2 * i + 1);
                }
                if (list.get(2 * i + 2) != null) {
                    list.get(i).right = list.get(2 * i + 2);
                }
            }
        }

        return list.get(0);
    }
}
