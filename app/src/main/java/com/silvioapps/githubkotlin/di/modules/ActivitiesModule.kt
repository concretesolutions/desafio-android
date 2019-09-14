package com.silvioapps.githubkotlin.di.modules

import com.silvioapps.githubkotlin.features.details.activities.DetailsActivity
import com.silvioapps.githubkotlin.features.list.activities.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesDetailsActivity(): DetailsActivity
}
