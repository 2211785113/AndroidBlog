package com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import static com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree.CreateTree.strToTree;

/**
 * 未完待续
 */
public class A606 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a606);

        String[] arr = new String[]{"1", "2", "3", "4"};
        String[] arr1 = new String[]{"1", "2", "3", null, "4"};
        TreeNode node = strToTree(arr);
        String res = tree2str(node);
        Log.d(getClass().getName(), "onCreate: res=" + res);
    }

    public String tree2str(TreeNode t) {
        if (t == null) {
            return "()";
        }

        Log.d(getClass().getName(), (String) t.value);
        tree2str(t.left);
        tree2str(t.right);

        return "";
    }
}
