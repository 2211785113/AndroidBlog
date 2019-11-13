package com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.operate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.bean.Node;

import static com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.create.CreateLink.strToLink;

public class UpdateNode extends AppCompatActivity {

    private String[] str;
    private Node node;
    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_node);

        //创建链表
        str = new String[]{"A", "B", "C", "D", "E", "F", "G"};
        node = strToLink(str);
        size = str.length;

        //改
        updateNode(2, "K");
    }

    private void updateNode(int index, String newVal) {

        Node indexNode = node(index);
        indexNode.e = newVal;

        for (Node x = node; x != null; x = x.next) {
            Log.d(getClass().getName(), "updateNode:x== " + x.e);
        }
    }

    /**
     * 查：根据index查对应的node
     */
    private Node node(int index) {
        Node x = node;

        for (int i = 0; i < index; i++)
            x = x.next;

        return x;
    }
}
