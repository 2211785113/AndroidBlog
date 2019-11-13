package com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.operate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.bean.Node;

import static com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.create.CreateLink.strToLink;

public class RemoveNode extends AppCompatActivity {

    private Node first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_node);

        String[] str = new String[]{"A", "B", "C", "o", "p", "q", "D", "E", "F"};
        first = strToLink(str);

        removeNode(4);//p
    }

    private void removeNode(int index) {
        Node remvNod = node(index);
        Node predNod = node(index - 1);
        Node succNod = remvNod.next;

        predNod.next = succNod;

        remvNod.e = null;
        remvNod.next = null;

        for (Node x = first; x != null; x = x.next) {
            Log.d(getClass().getName(), "removeNode:node== " + x.e);
        }
    }

    private Node node(int index) {
        Node x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }
}
