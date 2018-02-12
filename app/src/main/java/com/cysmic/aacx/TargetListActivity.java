package com.cysmic.aacx;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cysmic.aacx.model.ConnectivityViewModel;
import com.cysmic.aacx.model.Target;
import com.cysmic.aacx.model.TargetListViewModel;
import com.cysmic.aacx.ui.TargetRecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

public class TargetListActivity extends AppCompatActivity {
  @Inject ViewModelProvider.Factory viewModelProviderFactory;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_target_list);

    final TargetListViewModel model = ViewModelProviders.of(this, viewModelProviderFactory).get(TargetListViewModel.class);
    final ConnectivityViewModel connectivity = ViewModelProviders.of(this).get(ConnectivityViewModel.class);

    final TextView message = findViewById(R.id.target_list_message);

    final RecyclerView recyclerView = findViewById(R.id.item_recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    final TargetRecyclerViewAdapter adapter = new TargetRecyclerViewAdapter(model);
    recyclerView.setAdapter(adapter);

    model.getData().observe(this, new Observer<List<Target>>() {
      @Override
      public void onChanged(@Nullable List<Target> items) {
        adapter.notifyDataSetChanged();
      }
    });
    model.getMessage().observe(this, new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        message.setText(s);
      }
    });

    connectivity.getData().observe(this, new Observer<Boolean>() {
      @Override
      public void onChanged(@Nullable Boolean isConnected) {
        if (!Boolean.TRUE.equals(isConnected)) {
          message.setText(R.string.online_warning);
        }
        else if (model.loadData()) {
          message.setText(R.string.fetching_items);
        }
        else {
          message.setText(R.string.online_message);
        }
      }
    });

    final Button sortName = findViewById(R.id.sort_name_button);
    sortName.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        model.sortByName();
      }
    });

    final Button sortResult = findViewById(R.id.sort_result_button);
    sortResult.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        model.sortByResult();
      }
    });
  }
}
