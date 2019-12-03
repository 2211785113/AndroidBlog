package com.example.ruru.sophiesblog.android.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import com.example.ruru.sophiesblog.android.adapter.RecvAdapter.MyViewHolder

class RecvAdapter : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        return null
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {}
    override fun getItemCount(): Int {
        return 0
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    internal inner class MyViewHolder(itemView: View) : ViewHolder(itemView)
}