package com.pedrenrique.githubapp.core.di.modules.features

import com.pedrenrique.githubapp.core.data.datasource.RepositoryDataSource
import com.pedrenrique.githubapp.core.domain.ListRepositories
import com.pedrenrique.githubapp.core.domain.LoadMoreRepositories
import com.pedrenrique.githubapp.core.net.services.GithubService
import com.pedrenrique.githubapp.features.pr.PullRequestsFragment
import com.pedrenrique.githubapp.features.repositories.RepositoriesFragment
import com.pedrenrique.githubapp.features.repositories.RepositoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoriesModule = module {
    scope(named<RepositoriesFragment>()) {
        viewModel {
            RepositoriesViewModel(get(), get())
        }

        factory {
            ListRepositories(get())
        }

        factory {
            LoadMoreRepositories(get())
        }

        factory<RepositoryDataSource> {
            RepositoryDataSource.Impl(get())
        }
    }
}