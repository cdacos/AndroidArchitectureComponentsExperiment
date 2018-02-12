package com.cysmic.aacx.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;

import com.cysmic.aacx.net.FetchFeedAsync;
import com.cysmic.aacx.net.FetchResponse;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class TargetRepository implements FetchResponse {
  private final MutableLiveData<List<Target>> data = new MutableLiveData<>();
  private final MutableLiveData<String> message = new MutableLiveData<>();
  private final ThreadPoolExecutor poolExecutor;

  @Inject TargetSort itemSort;
  @Inject TargetMeasure itemMeasure;
  @Inject FetchFeedAsync fetchFeedAsync;

  @Inject
  public TargetRepository() {
    final BlockingQueue<Runnable> requestQueue = new LinkedBlockingQueue<>();
    final int corePoolSize = Runtime.getRuntime().availableProcessors();
    this.poolExecutor = new ThreadPoolExecutor(
        corePoolSize,
        4 * corePoolSize,
        1,
        TimeUnit.SECONDS,
        requestQueue);
  }

  public void loadData() {
    fetchFeedAsync.executeWithCallback(this);
  }

  public LiveData<List<Target>> getData() {
    return data;
  }

  public LiveData<String> getFakeMessage() {
    return message;
  }

  // Returns the first that matches - url _should_ be unique?
  protected Target getTargetFromUrl(String url) {
    if (data.getValue() != null) {
      for (Target item : data.getValue()) {
        if (item.getUrl().equalsIgnoreCase(url)) {
          return item;
        }
      }
    }
    return null;
  }

  public void sortData(TargetSort.SortBy sortBy) {
    data.setValue(itemSort.sortData(data.getValue(), sortBy));
  }

  public void measure(final String url) {
    Target item = getTargetFromUrl(url);
    if (item != null) measureTarget(item);
  }

  private void measureTarget(final Target item) {
    poolExecutor.execute(new Runnable() {
      @Override
      public void run() {
        item.setResult(0); // UI can use 0 value to show measurement underway
        data.postValue(data.getValue()); // Don't sort just yet
        // Measure...
        item.setResult(itemMeasure.measure(item));
        data.postValue(itemSort.sortData(data.getValue()));
      }
    });
  }

  @Override
  public void onFetchResponse(List<Target> list, String message) {
    if (list != null) {
      data.setValue(list);
      this.message.setValue(String.format(Locale.getDefault(), "Found %s items", list.size()));
      for (Target item : list) {
        measureTarget(item);
      }
    }
    else if (!TextUtils.isEmpty(message)) {
      this.message.setValue(message);
    }
    else {
      this.message.setValue("No items found");
    }
  }
}
