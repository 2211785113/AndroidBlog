package com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree;

import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

import java.util.LinkedList;
import java.util.Queue;

import androidx.appcompat.app.AppCompatActivity;

public class ANextSibil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_sibil);
    }

    public void nextSibiling(TreeNode node) {
        if (node == null) {
            return;
        }

        Queue queue = new LinkedList();
        queue.add(node);
        //这个level没有实际用处，但是可以告诉大家怎么判断当前node是第几层。
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            //用这个for循环，可以保证for循环里面对queue不管加多少个子节点，我只处理当前层里面的节点
            for (int i = 0; i < size; i++) {
                //把当前第一个节点拿出来
                TreeNode current = (TreeNode) queue.poll();
                //把子节点加到queue里面
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }

                if (i != size - 1) {
                    //peek只是获取当前队列中第一个节点，但是并不把它从队列中拿出来
                    current.next = (TreeNode) queue.peek();
                }
            }
        }
        level++;
    }
}
