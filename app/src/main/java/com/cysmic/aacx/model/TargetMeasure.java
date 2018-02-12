package com.cysmic.aacx.model;

import android.util.Log;

import com.cysmic.aacx.net.InternetUtils;

import java.util.Locale;

import javax.inject.Inject;

// Don't run on the UI thread...
public class TargetMeasure {
  private static final String TAG = TargetMeasure.class.getName();
  private static final int CHECKS = 5;

  @Inject InternetUtils internetUtils;

  @Inject
  public TargetMeasure() { }

  float measure(Target item) {
    Log.d(TAG, item.getName());
    float newResult = 0;
    item.setError(null);
    for (int i=0; i<CHECKS; i++) {
      newResult += measureTarget(item);
      if (item.getError() != null) return -1;
      Log.d(TAG, String.format(Locale.getDefault(), "%s | %s -> %s", item.getName(), item.getUrl(), newResult));
    }
    return (newResult / CHECKS);
  }

  private float measureTarget(Target item) {
    try {
      return internetUtils.ping(item.getUrl());
    }
    catch (Exception e) {
      item.setError(e.getLocalizedMessage());
      return -1;
    }
  }
}
