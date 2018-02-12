package com.cysmic.aacx.net;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.cysmic.aacx.model.Target;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class FetchFeedAsync extends AsyncTask<Void, Void, Target[]> {
  private FetchResponse callback;
  private String message;

  @Inject
  public FetchFeedAsync() { }

  public void executeWithCallback(@NonNull FetchResponse callback) {
    this.callback = callback;
    if (!getStatus().equals(Status.RUNNING)) execute();
  }

  @Override
  protected Target[] doInBackground(Void... voids) {
    try {
      URL url = new URL("https://raw.githubusercontent.com/cdacos/AndroidArchitectureComponentsExperiment/master/feed.json");
      InputStreamReader reader = new InputStreamReader(url.openStream());
      return new Gson().fromJson(reader, Target[].class);
    }
    catch (final Exception e) {
      message = String.format(Locale.getDefault(), "Error loading feed: %s. Check your connectivity.", e.getLocalizedMessage());
    }
    return null;
  }

  @Override
  protected void onPostExecute(Target[] items) {
    List<Target> list = items != null ? Arrays.asList(items) : null;
    if (callback != null) callback.onFetchResponse(list, message);
  }
}
