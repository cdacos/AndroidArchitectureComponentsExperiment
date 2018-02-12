package com.cysmic.aacx.ui;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cysmic.aacx.R;
import com.cysmic.aacx.model.TargetListViewModel;

public class TargetRecyclerViewAdapter extends RecyclerView.Adapter<TargetRecyclerViewHolder> {
  private final TargetListViewModel model;

  public TargetRecyclerViewAdapter(TargetListViewModel model) {
    this.model = model;
  }

  @SuppressLint ("InflateParams")
  @Override
  public TargetRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.target_details, parent, false);
    return new TargetRecyclerViewHolder(model, view);
  }

  @Override
  public void onBindViewHolder(TargetRecyclerViewHolder holder, int position) {
    holder.bindData(model.getTargets().get(position));
  }

  @Override
  public int getItemCount() {
    return model.getTargets().size();
  }
}
