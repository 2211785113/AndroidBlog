package com.example.ruru.sophiesblog.android.file

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ruru.sophiesblog.R
import kotlinx.android.synthetic.main.activity_file_system.*

class FileSystem : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_system)

        //云盘文件，手机存储
        btn_file.setOnClickListener { view ->
            var intent = Intent(this, FileListActivity::class.java)
            startActivity(intent)
        }
    }
}
