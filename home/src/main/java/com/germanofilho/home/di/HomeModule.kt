package com.germanofilho.home.di

import com.germanofilho.home.presentation.viewmodel.HomeViewModel
import com.germanofilho.home.repository.HomeRepository
import com.germanofilho.home.repository.HomeRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

    val viewModelModule = module {
        viewModel { HomeViewModel(get()) }
    }

    val repositoryModule = module {
        single<HomeRepository> { HomeRepositoryImpl() }
    }

    object HomeModule : KoinComponent {
        fun inject() =
            loadKoinModules(
                listOf(
                    viewModelModule,
                    repositoryModule
                )
            )
    }

