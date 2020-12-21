package com.ccortez.desafioconcrete.di.module

import com.ccortez.desafioconcrete.ui.main.RepositoryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeRepositoryFragment(): RepositoryFragment

//    @ContributesAndroidInjector
//    abstract fun contributeRepositoryListFragment(): RepositoryListFragment

}