package com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Stack;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree.CreateTree.createTree;
import static com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree.CreateTree.strToTree;
import static com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree.CreateTree.strToTree1;

/**
 * 中序遍历:DBEAFCG
 */
public class BinTreeInOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_tree_inorder_traversal);

        String[] str = new String[]{"a", "b", "c", "d", "e", "f", "g"};
        TreeNode node = strToTree1(str);
//        inOrder(node);
//        inOrder1(node);
        inOrder2(node);
    }

    /**
     * 递归
     *
     * @param node
     */
    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        Log.d(getClass().getName(), (String) node.value);
        inOrder(node.right);
    }

    /**
     * 非递归
     *
     * @param node
     */
    private void inOrder1(TreeNode node) {
        if (node != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || node != null) {
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    node = stack.pop();
                    Log.d(getClass().getName(), (String) node.value);
                    node = node.right;
                }
            }
        }
    }

    /**
     * 非递归:
     * 若其左孩子不为空，则将P入栈并将P的左孩子置为当前的P，然后对当前结点P再进行相同的处理；
     * 若其左孩子为空，则取栈顶元素并进行出栈操作，访问该栈顶结点，然后将当前的P置为栈顶结点的右孩子；
     * 直到P为NULL并且栈为空则遍历结束。
     */
    private void inOrder2(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                Log.d(getClass().getName(), (String) node.value);
                node = node.right;
            }
        }
    }
}
