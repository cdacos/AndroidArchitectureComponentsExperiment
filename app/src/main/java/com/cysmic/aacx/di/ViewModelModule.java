package com.cysmic.aacx.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.cysmic.aacx.model.TargetListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {
  @Binds
  @IntoMap
  @ViewModelKey(TargetListViewModel.class)
  abstract ViewModel bindTargetListViewModel(TargetListViewModel targetListViewModel);

  @Binds
  abstract ViewModelProvider.Factory bindViewModelFactory(GithubViewModelFactory factory);
}
