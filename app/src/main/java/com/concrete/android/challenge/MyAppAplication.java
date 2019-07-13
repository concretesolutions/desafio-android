package com.concrete.android.challenge;

import android.app.Activity;
import android.app.Application;
import androidx.fragment.app.Fragment;
import com.concrete.android.challenge.di.component.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;
import javax.inject.Inject;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class MyAppAplication extends Application implements HasActivityInjector,
    HasSupportFragmentInjector {

  @Inject
  DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

  @Inject
  DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

  @Override
  public DispatchingAndroidInjector<Activity> activityInjector() {
    return activityDispatchingAndroidInjector;
  }

  @Override public AndroidInjector<Fragment> supportFragmentInjector() {
    return fragmentDispatchingAndroidInjector;
  }

  @Override
  public void onCreate() {
    super.onCreate();

    DaggerAppComponent.builder()
        .application(this)
        .build()
        .inject(this);
  }
}
