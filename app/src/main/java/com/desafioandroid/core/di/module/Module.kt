package com.desafioandroid.core.di.module

import com.desafioandroid.core.service.ApiClient
import com.desafioandroid.feature.home.repository.HomeRepository
import com.desafioandroid.data.source.remote.ApiService
import com.desafioandroid.feature.home.presentation.viewmodel.HomeViewModel
import com.desafioandroid.feature.pullrequest.presentation.viewmodel.PullRequestViewModel
import com.desafioandroid.feature.pullrequest.repository.PullRequestRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<HomeRepository> { HomeRepository(get()) }
    single<PullRequestRepository> { PullRequestRepository(get()) }
}

val viewModelModule = module {
    viewModel<HomeViewModel> { HomeViewModel(get()) }
    viewModel<PullRequestViewModel> { PullRequestViewModel(get()) }
}

val apiServiceClientModule = module {
    factory<ApiService> { ApiClient().apiService }
}
