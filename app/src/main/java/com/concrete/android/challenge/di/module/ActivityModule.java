package com.concrete.android.challenge.di.module;

import com.concrete.android.challenge.ui.main.MainActivity;
import com.concrete.android.challenge.ui.pull_request.PullRequestActivity;
import com.concrete.android.challenge.ui.splash.SplashActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Module
public abstract class ActivityModule {

  @ContributesAndroidInjector
  abstract SplashActivity bindSplashActivity();

  @ContributesAndroidInjector
  abstract MainActivity bindMainActivity();

  @ContributesAndroidInjector(modules = PullRequestFactoryModule.class)
  abstract PullRequestActivity bindPullRequestActivity();
}
