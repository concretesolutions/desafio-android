package dev.theuzfaleiro.trendingongithub.di.factory

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.theuzfaleiro.trendingongithub.app.TrendingOnGitHubApplication
import dev.theuzfaleiro.trendingongithub.di.builder.ActivityBuilder
import dev.theuzfaleiro.trendingongithub.network.di.RetrofitModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (ActivityBuilder::class), (RetrofitModule::class)])
interface AppComponent : AndroidInjector<TrendingOnGitHubApplication> {

    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<TrendingOnGitHubApplication>
}