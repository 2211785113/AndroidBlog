package com.example.ruru.sophiesblog.data_algorithm.datastructure.linked._add

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ruru.sophiesblog.R
import com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.bean.Node
import com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.create.CreateLink.strToLink

class AddNode : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_node)

        val str = arrayOf("A", "B", "C", "D", "E", "F", "G")
        val node = strToLink(str)

        var q = Node("D", null);
        var p = Node("C", q);

        var newNode = Node("CC", null);
        addNode(p, q, newNode);
    }

    private fun addNode(p: Node<String>, q: Node<String>, newNode: Node<String>) {

    }
}
