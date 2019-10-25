package com.example.eloyvitorio.githubjavapop.di

import com.example.eloyvitorio.githubjavapop.data.network.CreateRetrofit
import com.example.eloyvitorio.githubjavapop.data.network.CreateRetrofitImpl
import com.example.eloyvitorio.githubjavapop.data.network.PullRequestsAPI
import com.example.eloyvitorio.githubjavapop.data.network.RepositoriesAPI
import com.example.eloyvitorio.githubjavapop.data.network.RepositoriesAPIImpl
import com.example.eloyvitorio.githubjavapop.data.network.PullRequestsAPIImpl
import com.example.eloyvitorio.githubjavapop.ui.pullrequest.PullRequestsViewModel
import com.example.eloyvitorio.githubjavapop.ui.repositories.RepositoriesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single <CreateRetrofit> { CreateRetrofitImpl() }
    single <RepositoriesAPI> { RepositoriesAPIImpl(get()) }
    single <PullRequestsAPI> { PullRequestsAPIImpl(get()) }
    viewModel { RepositoriesViewModel(get()) }
    viewModel { PullRequestsViewModel(get()) }
}