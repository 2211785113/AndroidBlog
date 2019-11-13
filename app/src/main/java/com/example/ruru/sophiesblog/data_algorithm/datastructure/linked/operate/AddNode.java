package com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.operate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.bean.Node;

import static com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.create.CreateLink.strToLink;

public class AddNode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_node2);

        Node q = new Node("q", null);
        Node p = new Node("p", q);

        addNode("CC", p, q);
    }

    /**
     * 添加结点在p和q之间
     */
    private void addNode(String e, Node p, Node q) {
        Node<String> newNode = new Node<>(e, null);
        p.next = newNode;
        newNode.next = q;

        for (Node x = p; x != null; x = x.next) {
            Log.d(getClass().getName(), "addNode:node== " + x.e);
        }
    }
}
