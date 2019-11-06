package com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Stack;

import static com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree.CreateTree.createTree;

/**
 * 后序遍历:DEBFGCA
 */
public class BinTreePostOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_tree_postorder_traversal);

        TreeNode node = createTree();
//        postOrder(node);
        postOrder1(node);
    }

    /**
     * 递归
     *
     * @param node
     */
    private void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        Log.d(getClass().getName(), (String) node.value);
    }

    /**
     * 非递归：双栈法
     *
     * @param node
     */
    private void postOrder1(TreeNode node) {
        if (node != null) {
            Stack<TreeNode> s1 = new Stack<TreeNode>();
            Stack<TreeNode> s2 = new Stack<TreeNode>();
            s1.push(node);
            while (!s1.isEmpty()) {
                node = s1.pop();
                s2.push(node);
                if (node.left != null) {
                    s1.push(node.left);
                }
                if (node.right != null) {
                    s1.push(node.right);
                }
            }
            while (!s2.isEmpty()) {
                Log.d(getClass().getName(), s2.pop().value + " ");
            }
        }
    }

    /**
     * 非递归：
     *
     * @param node
     */
    private void postOrder2(TreeNode node) {
        if (node != null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(node);
            TreeNode c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && node != c.left && node != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && node != c.right) {
                    stack.push(c.right);
                } else {
                    Log.d(getClass().getName(), (String) stack.pop().value);
                    node = c;
                }
            }
        }
    }
}
