package com.example.ruru.sophiesblog.android.ui.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.ruru.sophiesblog.R

class MultiCheckAdapter(layoutResId: Int, list: MutableList<String>): BaseQuickAdapter<String, BaseViewHolder>(layoutResId, list) {
  override fun convert(holder: BaseViewHolder, item: String) {
    holder.getView<TextView>(R.id.item_tv_name).text = item
  }
}

