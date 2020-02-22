package com.example.ruru.sophiesblog.android.file

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ruru.sophiesblog.R
import kotlinx.android.synthetic.main.activity_file_list.*

class FileListActivity : AppCompatActivity() {

    private var fileType = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_list)

        when (fileType) {
            0 -> {
                toolBar.title = getString(R.string.cloudFile)
                chooseFileType.text = getString(R.string.cloudFile)
            }
            1 -> {
                toolBar.title = getString(R.string.phoneStore)
                chooseFileType.text = getString(R.string.phoneStore)
            }
        }

        chooseFileType.setOnClickListener { view ->
            val dialog = Dialog(this)
            val view = LayoutInflater.from(this).inflate(R.layout.dialog_file, null)
            view.findViewById<TextView>(R.id.cloudFile).setOnClickListener { view ->
                fileType = 0
            }
            view.findViewById<TextView>(R.id.phoneStore).setOnClickListener { view ->
                fileType = 1
            }
            dialog.setContentView(view)
            dialog.show()
        }

        recv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        var adapter = FileAdapter(this, listOf("aa"))
        adapter.setData()
        recv.adapter = adapter
    }
}
