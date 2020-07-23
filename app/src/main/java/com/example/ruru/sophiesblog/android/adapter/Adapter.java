package com.example.ruru.sophiesblog.android.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by lyr on 2019/12/8
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

  @NonNull
  @Override
  public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return null;
  }

  @Override
  public void onBindViewHolder(@NonNull Adapter.MyViewHolder viewHolder, int i) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }

  class MyViewHolder extends RecyclerView.ViewHolder {

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }
}
