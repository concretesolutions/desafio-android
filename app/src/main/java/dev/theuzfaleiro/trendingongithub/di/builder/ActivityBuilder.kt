package dev.theuzfaleiro.trendingongithub.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.theuzfaleiro.trendingongithub.di.scope.PerActivity
import dev.theuzfaleiro.trendingongithub.ui.feature.home.HomeActivity
import dev.theuzfaleiro.trendingongithub.ui.feature.home.di.HomeModule

@Module
internal abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = [HomeModule::class])
    internal abstract fun homeActivityInjector(): HomeActivity
}