package com.example.lucasdias.mvvmpoc.di

import com.example.lucasdias.mvvmpoc.data.repository.RepositoryRepositoryImp
import com.example.lucasdias.mvvmpoc.domain.repository.RepositoryRepository
import com.example.lucasdias.mvvmpoc.domain.useCase.RepositoryUseCase
import com.example.lucasdias.mvvmpoc.presentation.ui.repositories.RepositoryActivity
import com.example.lucasdias.mvvmpoc.presentation.ui.repositories.RepositoryAdapter
import com.example.lucasdias.mvvmpoc.presentation.ui.repositories.RepositoryViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val repositoryViewModule = module {
    viewModel{ RepositoryViewModel(get(), get()) }
    single { (view: RepositoryActivity) -> RepositoryAdapter(view) }
}

val repositoryViewModelModule = module {
    factory { RepositoryUseCase(get()) }
}

val repositoryRepositoryModule = module {}

val repositoryServiceModule = module {  }

val repositoryUseCaseModule = module {
    factory { RepositoryRepositoryImp(get(), get()) as RepositoryRepository }
}