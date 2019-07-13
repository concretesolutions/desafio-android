package com.concrete.android.challenge;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.concrete.android.challenge.data.remote.GithubService;
import com.concrete.android.challenge.ui.main.MainViewModel;
import com.concrete.android.challenge.ui.pull_request.PullRequestViewModel;
import com.concrete.android.challenge.ui.repository.RepositoryViewModel;
import com.concrete.android.challenge.ui.splash.SplashViewModel;
import com.concrete.android.challenge.utils.rx.SchedulerProvider;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

  private GithubService service;
  private SchedulerProvider schedulerProvider;

  @Inject
  public ViewModelFactory(GithubService service,
      SchedulerProvider schedulerProvider) {
    this.service = service;
    this.schedulerProvider = schedulerProvider;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    if (modelClass.isAssignableFrom(RepositoryViewModel.class)) {
      //noinspection unchecked
      return (T) new RepositoryViewModel(service, schedulerProvider);
    } else if (modelClass.isAssignableFrom(MainViewModel.class)) {
      //noinspection unchecked
      return (T) new MainViewModel(service, schedulerProvider);
    } else if (modelClass.isAssignableFrom(PullRequestViewModel.class)) {
      //noinspection unchecked
      return (T) new PullRequestViewModel(service, schedulerProvider);
    } else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
      //noinspection unchecked
      return (T) new SplashViewModel(service, schedulerProvider);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}
