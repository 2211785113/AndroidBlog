package com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.LinkedList;
import java.util.Queue;

import static com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree.CreateTree.createTree;

/**
 * 层序遍历：ABCDEFG
 */
public class BinTreeLevelOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin_tree_level_order);

        TreeNode node = createTree();
        levelOrder(node);
    }

    /**
     * 队列
     *
     * @param node
     */
    private void levelOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        Queue queue = new LinkedList();
        queue.add(node);
        while (!queue.isEmpty()) {
            node = (TreeNode) queue.poll();
            Log.d(getClass().getName(), (String) node.value);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
}
