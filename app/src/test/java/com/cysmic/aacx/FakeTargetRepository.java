package com.cysmic.aacx;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.cysmic.aacx.model.Target;
import com.cysmic.aacx.model.TargetRepository;
import com.cysmic.aacx.model.TargetSort;

import java.util.ArrayList;
import java.util.List;

public class FakeTargetRepository extends TargetRepository {
  private MutableLiveData<List<Target>> fakeData;
  private MutableLiveData<String> fakeMessage = new MutableLiveData<>();

  FakeTargetRepository() { }

  @Override
  public LiveData<List<Target>> getData() {
    return fakeData;
  }

  @Override
  public LiveData<String> getFakeMessage() {
    return fakeMessage;
  }

  @Override
  public void loadData() {
    if (fakeData == null) {
      fakeData = new MutableLiveData<>();
    }
    if (fakeData.getValue() == null) {
      ArrayList<Target> list = new ArrayList<>();
      list.add(new Target("Three", "www.bbc.co.uk", "https://www.bbc.co.uk/favicon.ico"));
      list.add(new Target("One", "www.google.com", "https://www.google.com/favicon.ico"));
      list.add(new Target("Two", "www.google.co.uk", "https://www.google.com/favicon.ico"));
      fakeData.setValue(list);
    }
  }

  @Override
  public void measure(String url) {
    Target target = getTargetFromUrl(url);
    if (target != null) {
      target.setResult(100);
    }
    fakeData.setValue(fakeData.getValue());
  }

  @Override
  public void onFetchResponse(List<Target> list, String message) {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public void sortData(TargetSort.SortBy sortBy) {
    throw new RuntimeException("Not implemented");
  }
}
