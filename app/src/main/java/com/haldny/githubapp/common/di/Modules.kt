package com.haldny.githubapp.common.di

import com.haldny.githubapp.domain.repository.*
import com.haldny.githubapp.presentation.main.MainViewModel
import com.haldny.githubapp.presentation.pull.PullRequestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<IGithubRepository> { GithubRepository(get()) }
    single<IPullRequestRepository> { PullRequestRepository(get()) }
}

val viewModelModule = module {
    viewModel<MainViewModel> { MainViewModel(get()) }
    viewModel<PullRequestViewModel> { (userName: String, repositoryName: String) ->
        PullRequestViewModel(userName, repositoryName, get())
    }
}

val gitHubApiClientModule = module {
    factory<GithubApi> { GithubApiClient().githubApi }
}

val allModule = listOf(
    repositoryModule,
    viewModelModule,
    gitHubApiClientModule
)