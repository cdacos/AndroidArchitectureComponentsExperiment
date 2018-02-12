package com.cysmic.aacx.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.cysmic.aacx.App;

import dagger.android.AndroidInjection;

// Automatically inject the activities
public class AppInjector {
  private AppInjector() {}

  public static void init(App app) {
    DaggerAppComponent.builder().application(app).build().inject(app);

    app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
          @Override
          public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            AndroidInjection.inject(activity);
          }

          @Override
          public void onActivityStarted(Activity activity) { }

          @Override
          public void onActivityResumed(Activity activity) { }

          @Override
          public void onActivityPaused(Activity activity) { }

          @Override
          public void onActivityStopped(Activity activity) { }

          @Override
          public void onActivitySaveInstanceState(Activity activity, Bundle outState) { }

          @Override
          public void onActivityDestroyed(Activity activity) { }
        });
  }
}