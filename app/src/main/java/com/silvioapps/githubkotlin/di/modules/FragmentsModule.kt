package com.silvioapps.githubkotlin.di.modules

import com.silvioapps.githubkotlin.features.details.fragments.DetailsFragment
import com.silvioapps.githubkotlin.features.list.fragments.MainFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {
    @ContributesAndroidInjector
    abstract fun contributesMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributesDetailsFragment(): DetailsFragment
}
