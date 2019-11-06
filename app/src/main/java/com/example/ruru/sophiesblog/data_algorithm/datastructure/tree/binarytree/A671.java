package com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import static com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree.CreateTree.strToTree;
import static com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree.CreateTree.strToTree1;

/**
 * 思想：DFS/递归
 */
public class A671 extends AppCompatActivity {

    private int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a671);

        String[] arr = new String[]{"2", "2", "5", null, null, "5", "7"};//5
        String[] arr1 = new String[]{"2", "2", "5", "3", "4", "5", "7","8"};//3
        String[] arr2 = new String[]{"2", "2", "2"};//-1

        TreeNode<String> node = strToTree(arr1);
        int res = findSecondMinimumValue1(node);

        Log.d(getClass().getName(), "onCreate: res=" + res);
    }

    /**
     * 递归
     *
     * @param node
     * @return
     */
    public int findSecondMinimumValue(TreeNode<String> node) {
        helper(node);
        return count == 0 ? -1 : second;
    }

    public void helper(TreeNode<String> node) {
        if (node == null)
            return;

        if (node.value == null)
            return;

        int val = Integer.parseInt(node.value);

        //得出first的值
        if (val < first) {
            second = first;
            first = val;
        } else if (val > first && val <= second) {
            //大于first的值；限制second的值
            count++;
            second = val;
        }
        helper(node.left);
        helper(node.right);
    }

    /*===============================================改进版:OKay===============================================================*/
    public int findSecondMinimumValue1(TreeNode<String> node) {
        int first = Integer.parseInt(node.value);
        helper1(node, first);
        return count == 0 ? -1 : second;
    }

    public void helper1(TreeNode<String> node, int first) {

        if (node == null)
            return;

        if (node.value == null)
            return;

        helper1(node.left, first);
        helper1(node.right, first);

        int val = Integer.parseInt(node.value);

        if (val > first && val <= second) {
            count++;
            second = val;
        }
    }
}
