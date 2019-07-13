package com.concrete.android.challenge.di.module;

import com.concrete.android.challenge.ui.repository.RepositoryFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Module
public abstract class FragmentModule {

  @ContributesAndroidInjector(modules = RepositoryFactoryModule.class)
  abstract RepositoryFragment bindRepository();
}
