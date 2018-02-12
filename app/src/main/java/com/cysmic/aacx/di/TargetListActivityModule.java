package com.cysmic.aacx.di;

import com.cysmic.aacx.TargetListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TargetListActivityModule {
  @ContributesAndroidInjector
  abstract TargetListActivity contributeTargetListActivity();
}

