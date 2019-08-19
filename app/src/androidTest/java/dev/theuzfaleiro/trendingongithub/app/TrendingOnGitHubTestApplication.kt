package dev.theuzfaleiro.trendingongithub.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dev.theuzfaleiro.trendingongithub.di.component.DaggerAppTestComponent

class TrendingOnGitHubTestApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out TrendingOnGitHubTestApplication> =
        DaggerAppTestComponent.factory().create(this)
}