package com.pedrenrique.githubapp.core.di.modules.features

import com.pedrenrique.githubapp.core.data.datasource.RepositoryDataSource
import com.pedrenrique.githubapp.core.domain.ListPRFromRepository
import com.pedrenrique.githubapp.core.domain.LoadMorePRFromRepository
import com.pedrenrique.githubapp.core.net.services.GithubService
import com.pedrenrique.githubapp.features.pr.PullRequestsFragment
import com.pedrenrique.githubapp.features.pr.PullRequestsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val pullRequestModule = module {
    scope(named<PullRequestsFragment>()) {
        viewModel {
            PullRequestsViewModel(get(), get())
        }

        factory {
            ListPRFromRepository(get())
        }

        factory {
            LoadMorePRFromRepository(get())
        }

        factory<RepositoryDataSource> {
            RepositoryDataSource.Impl(get())
        }

        factory<GithubService> {
            get<Retrofit>().create(GithubService::class.java)
        }
    }
}