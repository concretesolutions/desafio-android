package br.com.henriqueoliveira.desafioandroidconcrete.di

import br.com.henriqueoliveira.desafioandroidconcrete.service.repository.RepositoryDataSource
import br.com.henriqueoliveira.desafioandroidconcrete.viewmodel.RepositoryListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val androidModule = module {

    viewModel { RepositoryListViewModel(get()) }
    single{ RepositoryDataSource(get()) }

}