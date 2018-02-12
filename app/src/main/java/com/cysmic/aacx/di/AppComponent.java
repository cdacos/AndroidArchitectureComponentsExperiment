package com.cysmic.aacx.di;

import android.app.Application;

import com.cysmic.aacx.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
    AndroidInjectionModule.class,
    AppModule.class,
    TargetListActivityModule.class
})
public interface AppComponent {
  @Component.Builder
  interface Builder {
    @BindsInstance Builder application(Application application);
    AppComponent build();
  }
  void inject(App app);
}
