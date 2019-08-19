package dev.theuzfaleiro.trendingongithub.di.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.theuzfaleiro.trendingongithub.app.TrendingOnGitHubTestApplication
import dev.theuzfaleiro.trendingongithub.di.builder.ActivityBuilder
import dev.theuzfaleiro.trendingongithub.network.di.RetrofitTestModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (ActivityBuilder::class), (RetrofitTestModule::class)])
internal interface AppTestComponent : AndroidInjector<TrendingOnGitHubTestApplication> {

    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<TrendingOnGitHubTestApplication>
}