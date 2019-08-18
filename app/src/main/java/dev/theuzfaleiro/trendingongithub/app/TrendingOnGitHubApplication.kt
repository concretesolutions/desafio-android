package dev.theuzfaleiro.trendingongithub.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dev.theuzfaleiro.trendingongithub.di.component.DaggerAppComponent

class TrendingOnGitHubApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out TrendingOnGitHubApplication> =
        DaggerAppComponent.factory().create(this)
}