package com.losingtimeapps.domain.di

import com.losingtimeapps.domain.boundary.BaseScheduler
import com.losingtimeapps.domain.boundary.GitHubRepository
import com.losingtimeapps.domain.boundary.ResponseBoundary
import com.losingtimeapps.domain.usercase.GetGitHubPullRequestsUseCase
import com.losingtimeapps.domain.usercase.GetGitHubRepoUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetGitHubPullRequestUseCase(
        gitHubRepository: GitHubRepository,
        baseScheduler: BaseScheduler
    ) = GetGitHubPullRequestsUseCase(gitHubRepository, baseScheduler)


    @Provides
    fun provideGetGitHubRepoUseCase(
        gitHubRepository: GitHubRepository,
        baseScheduler: BaseScheduler
    ) = GetGitHubRepoUseCase(gitHubRepository, baseScheduler)
}