package com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.traverse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.bean.Node;
import com.example.ruru.sophiesblog.java.collection.list.ArrayList;

import java.util.List;

import static com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.create.CreateLink.strToLink;

/**
 * 反向遍历/反转
 */
public class ReversalLink extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reversal_link);

        String[] str = {"A", "B", "C", "D", "E", "F", "G"};
        Node node = strToLink(str);

        reversal(node);
    }

    private void reversal(Node node) {
        List<Node> list = new ArrayList<>();
        for (Node x = node; x != null; x = x.next) {
            list.add(x);
        }

        int len = list.size();

        for (int i = len - 1; i > 0; i--) {
            list.get(i).next = list.get(i - 1);
            Log.d(getClass().getName(), "reversal:res= " + list.get(i).e);
        }

        list.get(0).next = null;

        Log.d(getClass().getName(), "reversal:res= " + list.get(0).e);
    }
}
