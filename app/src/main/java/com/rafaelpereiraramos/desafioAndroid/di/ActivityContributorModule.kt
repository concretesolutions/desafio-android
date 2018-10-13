package com.rafaelpereiraramos.desafioAndroid.di

import com.rafaelpereiraramos.desafioAndroid.view.RepoActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Rafael P. Ramos on 13/10/2018.
 */
@Module
abstract class ActivityContributorModule {

    @ContributesAndroidInjector
    abstract fun contributeRepoActivity(): RepoActivity
}