package com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.traverse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.bean.Node;

import static com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.create.CreateLink.strToLink;

/**
 * 正向遍历
 */
public class TraversalLink extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traversal_link);

        String[] str = {"A", "B", "C", "D", "E", "F", "G"};
        Node node = strToLink(str);

        traversal(node);
        int length = getLength(node);
        Log.d(getClass().getName(), "onCreate:res= " + length);//7
    }

    private void traversal(Node node) {
        for (Node x = node; x != null; x = x.next) {
            Log.d(getClass().getName(), "traversal:res= " + x.e);
        }
    }

    private int getLength(Node node) {
        int index = 0;
        for (Node x = node; x != null; x = x.next) {
            index++;
        }
        return index;
    }
}
