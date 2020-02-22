package com.example.ruru.sophiesblog.android.file

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ruru.sophiesblog.R

/**
 * https://www.cnblogs.com/figozhg/archive/2017/03/18/6576034.html
 */
class FileAdapter(context: Context, val items: List<String>) : RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    private var context: Context? = null

    init {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FileViewHolder(LayoutInflater.from(context).inflate(R.layout.item_file, parent, false))

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = 0

    class FileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: String) = with(itemView) {
        }
    }

    fun setData() {

    }
}