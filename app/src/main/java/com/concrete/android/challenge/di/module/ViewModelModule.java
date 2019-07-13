package com.concrete.android.challenge.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.concrete.android.challenge.ViewModelFactory;
import com.concrete.android.challenge.ui.main.MainViewModel;
import com.concrete.android.challenge.ui.pull_request.PullRequestViewModel;
import com.concrete.android.challenge.ui.repository.RepositoryViewModel;
import com.concrete.android.challenge.ui.splash.SplashViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Module
public abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(PullRequestViewModel.class)
  abstract ViewModel bindPullRequestViewModel(PullRequestViewModel pullRequestViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(RepositoryViewModel.class)
  abstract ViewModel bindRepositoryViewModel(RepositoryViewModel repositoryViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(MainViewModel.class)
  abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(SplashViewModel.class)
  abstract ViewModel bindSplashViewModel(SplashViewModel splashViewModel);

  @Binds
  abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
