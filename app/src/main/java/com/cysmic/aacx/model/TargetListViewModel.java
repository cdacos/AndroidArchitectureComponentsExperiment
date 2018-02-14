package com.cysmic.aacx.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TargetListViewModel extends ViewModel {
  private final TargetRepository repository;

  @Inject
  public TargetListViewModel(TargetRepository repository) {
    this.repository = repository;
  }

  public boolean loadData() {
    if (getData() == null || getData().getValue() == null || getData().getValue().size() == 0) {
      repository.loadData();
      return true;
    }
    return false;
  }

  public LiveData<List<Target>> getData() {
    return repository.getData();
  }

  public LiveData<String> getMessage() {
    return repository.getFakeMessage();
  }

  public void measure(String name) {
    repository.measure(name);
  }

  public void sortByName() {
    repository.sortData(TargetSort.SortBy.NAME);
  }

  public void sortByResult() {
    repository.sortData(TargetSort.SortBy.RESULT);
  }

  // Helper which always returns a list
  @NonNull
  public List<Target> getTargets() {
    List<Target> items = getData().getValue();
    return items != null ? items : new ArrayList<Target>();
  }
}
