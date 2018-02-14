package com.cysmic.aacx.ui;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cysmic.aacx.R;
import com.cysmic.aacx.glide.GlideApp;
import com.cysmic.aacx.model.Target;
import com.cysmic.aacx.model.TargetListViewModel;

import java.util.Locale;

class TargetRecyclerViewHolder extends RecyclerView.ViewHolder {
  private final TextView name;
  private final ImageView icon;
  private final TextView result;
  private final TargetListViewModel model;
  private final ImageButton measure;
  private final ProgressBar progress;

  TargetRecyclerViewHolder(TargetListViewModel model, View itemView) {
    super(itemView);
    this.model = model;
    name = itemView.findViewById(R.id.name);
    icon = itemView.findViewById(R.id.icon);
    result = itemView.findViewById(R.id.result);
    measure = itemView.findViewById(R.id.measure);
    progress = itemView.findViewById(R.id.progress);
  }

  void bindData(Target item) {
    if (item.getResult() == 0) {
      measure.setVisibility(View.GONE);
      progress.setVisibility(View.VISIBLE);
      result.setVisibility(View.INVISIBLE);
    }
    else {
      measure.setVisibility(View.VISIBLE);
      progress.setVisibility(View.GONE);
      result.setVisibility(View.VISIBLE);
    }
    measure.setTag(item.getName());
    String n = item.getName() + (!TextUtils.isEmpty(item.getError()) ? " - " + item.getError() : "");
    name.setText(n);
    if (item.getResult() < 0) {
      result.setText(R.string.error_code);
    }
    else {
      result.setText(String.format(Locale.getDefault(), "%.1f ms", item.getResult()));
    }
    GlideApp
        .with((ViewGroup)icon.getParent())
        .load(item.getIconUrl())
        .fitCenter()
        .into(icon);
    measure.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        model.measure((String)v.getTag());
      }
    });
  }
}
