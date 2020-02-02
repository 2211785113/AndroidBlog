package com.example.ruru.sophiesblog.android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ruru.sophiesblog.R
import com.example.ruru.sophiesblog.android.adapter.RecvAdapter.MyViewHolder
import kotlin.contracts.contract

class RecvAdapter(private val context: Context): RecyclerView.Adapter<RecvAdapter.MyViewHolder>() {

  override fun getItemCount(): Int {
    return 0
  }

  override fun getItemViewType(position: Int): Int {
    return super.getItemViewType(position)
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false)
    return MyViewHolder(view)
  }

  override fun onBindViewHolder(holder: MyViewHolder, i: Int) {
    holder.textView.text = "nihao"
  }

  inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var textView: TextView = view.findViewById(R.id.tv)
  }
}