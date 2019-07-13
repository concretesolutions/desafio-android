package com.concrete.android.challenge.di.module;

import com.concrete.android.challenge.ui.repository.RepositoryAdapter;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Module
public class RepositoryFactoryModule {

  @Provides
  RepositoryAdapter provideRepositoryAdapter() {
    return new RepositoryAdapter(new ArrayList<>());
  }
}
