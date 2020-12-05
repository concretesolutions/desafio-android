package com.ccortez.desafioconcreteapplication.di.module

import com.ccortez.desafioconcreteapplication.view.ui.RepositoryFragment
import com.ccortez.desafioconcreteapplication.view.ui.RepositoryListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeRepositoryFragment(): RepositoryFragment

    @ContributesAndroidInjector
    abstract fun contributeRepositoryListFragment(): RepositoryListFragment

}