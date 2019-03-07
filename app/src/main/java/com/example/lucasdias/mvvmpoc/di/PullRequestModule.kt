package com.example.lucasdias.mvvmpoc.di

import com.example.lucasdias.mvvmpoc.data.repository.PullRequestRepositoryImp
import com.example.lucasdias.mvvmpoc.domain.repository.PullRequestRepository
import com.example.lucasdias.mvvmpoc.domain.useCase.PullRequestUseCase
import com.example.lucasdias.mvvmpoc.presentation.ui.pullrequests.PullRequestActivity
import com.example.lucasdias.mvvmpoc.presentation.ui.pullrequests.PullRequestAdapter
import com.example.lucasdias.mvvmpoc.presentation.ui.pullrequests.PullRequestViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val pullRequestViewModule = module {
    viewModel{ PullRequestViewModel(get(), get()) }
    factory { (view: PullRequestActivity) -> PullRequestAdapter(view) }
}

val pullRequestViewModelModule = module {
    factory { PullRequestUseCase(get()) }
}

val pullRequestRepositoryModule = module {}

val pullRequestServiceModule = module {  }

val pullRequestUseCaseModule = module {
    factory { PullRequestRepositoryImp(get(), get()) as PullRequestRepository }

}