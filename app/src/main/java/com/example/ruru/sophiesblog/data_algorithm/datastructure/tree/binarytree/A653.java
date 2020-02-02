package com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.HashSet;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree.CreateTree.strToTree;

/**
 * 二叉搜索树：BST
 * <p>
 * 第一种：递归
 * 第二种：深度优先搜索DFS等价于先序遍历
 */
public class A653 extends AppCompatActivity {

    private boolean flag = false;
    private HashSet<Integer> set = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a653);

        String[] arr = {"5", "3", "6", "2", "4", null, "7"};
        TreeNode node = strToTree(arr);

        boolean res = findTarget(node, 9);//true
        boolean res1 = findTarget1(node, 28);//false
        Log.d(getClass().getName(), "onCreate:res= " + res);
    }

    /**
     * 第一种：递归
     */
    public boolean findTarget(TreeNode<String> node, int k) {
        if (node == null)
            return false;
        if (node.value == null)
            return false;

        int val = Integer.parseInt(node.value);
        int target = k - val;
        TreeNode foundNode = findV(node, target);

        if (foundNode != null && node != foundNode)
            return true;
        else
            return findTarget(node.left, k) || findTarget(node.right, k);
    }

    /**
     * 寻找某个值的节点
     */
    public TreeNode findV(TreeNode<String> node, int v) {
        if (node == null)
            return null;
        if (node.value == null) {
            return null;
        }

        int val = Integer.parseInt(node.value);
        if (val == v)
            return node;
        if (v < val)
            return findV(node.left, v);
        else
            return findV(node.right, v);
    }

    /**
     * 第二种：深度优先搜索等价于先序遍历
     */
    public boolean findTarget1(TreeNode root, int k) {
        dfs(root, k);
        return flag;
    }

    private void dfs(TreeNode<String> root, int k) {
        if (root != null && root.value != null && flag == false) {

            int val = Integer.parseInt(root.value);

            if (set.contains(val)) {
                flag = true;
                return;
            } else {
                set.add(k - val);
                dfs(root.left, k);
                dfs(root.right, k);
            }
        }
    }
}
