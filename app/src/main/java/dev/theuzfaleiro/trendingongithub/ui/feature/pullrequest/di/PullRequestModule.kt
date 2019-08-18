package dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.di

import dagger.Module
import dagger.Provides
import dev.theuzfaleiro.trendingongithub.network.GitHubEndpoint
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.repository.PullRequestRepository
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.viewmodel.PullRequestViewModelFactory

@Module
object PullRequestModule {

    @Provides
    @JvmStatic
    fun providesPullRequestRepository(gitHubEndpoint: GitHubEndpoint) =
        PullRequestRepository(gitHubEndpoint)

    @Provides
    @JvmStatic
    fun providesPullRequestViewModelFactory(pullRequestRepository: PullRequestRepository) =
        PullRequestViewModelFactory(pullRequestRepository)
}