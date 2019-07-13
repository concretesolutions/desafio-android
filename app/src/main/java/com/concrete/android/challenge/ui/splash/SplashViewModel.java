package com.concrete.android.challenge.ui.splash;

import android.os.Handler;
import com.concrete.android.challenge.data.remote.GithubService;
import com.concrete.android.challenge.ui.base.BaseViewModel;
import com.concrete.android.challenge.utils.rx.SchedulerProvider;

/**
 * @author Thiago Corredo
 * @since 2019-07-12
 */
public class SplashViewModel extends BaseViewModel<SplashNavigator> {

  public SplashViewModel(GithubService service,
      SchedulerProvider schedulerProvider) {
    super(service, schedulerProvider);

    new Handler().postDelayed(() -> getNavigator().openMainActivity(), 5000);
  }
}
