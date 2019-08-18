package dev.theuzfaleiro.trendingongithub.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.theuzfaleiro.trendingongithub.di.scope.PerActivity
import dev.theuzfaleiro.trendingongithub.ui.feature.home.HomeActivity
import dev.theuzfaleiro.trendingongithub.ui.feature.home.di.HomeModule
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.PullRequestActivity
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.di.PullRequestModule

@Module
internal abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = [HomeModule::class])
    internal abstract fun homeActivityInjector(): HomeActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [PullRequestModule::class])
    internal abstract fun pullRequestActivityInjector(): PullRequestActivity
}