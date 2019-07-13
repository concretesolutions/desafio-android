package com.concrete.android.challenge.di.module;

import com.concrete.android.challenge.ui.pull_request.PullRequestAdapter;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Module
public class PullRequestFactoryModule {

  @Provides
  PullRequestAdapter providePullRequestAdapter() {
    return new PullRequestAdapter(new ArrayList<>());
  }
}
