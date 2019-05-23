package com.uharris.desafio_android.di.modules

import com.uharris.desafio_android.presentation.sections.main.MainActivity
import com.uharris.desafio_android.presentation.sections.pull.PullRequestActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UIModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesPullRequestActivity(): PullRequestActivity
}