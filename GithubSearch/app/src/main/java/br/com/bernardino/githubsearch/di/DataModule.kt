package br.com.bernardino.githubsearch.di

import br.com.bernardino.githubsearch.repository.ReposRepository
import br.com.bernardino.githubsearch.repository.ReposRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val dataModule = module {

    single {
        ReposRepositoryImpl(
            dao = get(),
            api = get()
        )
    }
}