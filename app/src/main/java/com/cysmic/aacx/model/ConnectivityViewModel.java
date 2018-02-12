package com.cysmic.aacx.model;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

@SuppressLint ("StaticFieldLeak") // No, we hold the application context by design
public class ConnectivityViewModel extends AndroidViewModel {
  private final Application application;
  private final MutableLiveData<Boolean> data = new MutableLiveData<>();

  public ConnectivityViewModel(@NonNull Application application) {
    super(application);
    this.application = application;
    final BroadcastReceiver networkStateReceiver = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        data.setValue(isConnected());
      }
    };
    IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    application.registerReceiver(networkStateReceiver, filter);
  }

  public LiveData<Boolean> getData() {
    return data;
  }

  private boolean isConnected() {
    ConnectivityManager cm = (ConnectivityManager)application.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo = cm != null ? cm.getActiveNetworkInfo() : null;
    return netInfo != null && netInfo.isAvailable() && netInfo.isConnected();
  }
}
