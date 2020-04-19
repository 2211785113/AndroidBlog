package com.example.ruru.sophiesblog.android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ruru.library.BaseRecvAdapter
import com.example.ruru.library.viewholder.BaseViewHolder
import com.example.ruru.sophiesblog.R
import kotlinx.android.synthetic.main.activity_recv_diveder.*

class RecvDiveder: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_recv_diveder)

    var adapter: BaseRecvAdapter<String> = object: BaseRecvAdapter<String>(R.layout.layout_recv_item) {
      override fun bindData(viewHolder: BaseViewHolder?, t: String?) {
        viewHolder?.setText(R.id.tv, t)
      }
    }

    adapter.setData(arrayListOf("1", "2", "3", "4", "5"))

    recv.layoutManager = LinearLayoutManager(this@RecvDiveder, LinearLayoutManager.VERTICAL, false)
    recv.adapter = adapter
  }
}
