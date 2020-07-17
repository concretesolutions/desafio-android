package com.jsouza.repocatalog.di

import com.jsouza.repocatalog.data.repocatalog.paging.local.RepoRepositoryImpl
import com.jsouza.repocatalog.data.repocatalog.remote.RepositoryCatalogService
import com.jsouza.repocatalog.domain.repository.RepoRepository
import com.jsouza.repocatalog.domain.usecase.FetchReposFromApi
import com.jsouza.repocatalog.presentation.RepoCatalogViewModel
import com.jsouza.shared_components.di.SHARED_RETROFIT
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
val repositoryCatalogModule = module {

    viewModel {
        RepoCatalogViewModel(
            get<FetchReposFromApi>()
        )
    }

    single {
        RepoRepositoryImpl(
            get<RepositoryCatalogService>()
        ) as RepoRepository
    }

    single {
        FetchReposFromApi(
            get<RepoRepository>()
        )
    }

    single {
        getRepositoryService(
            get<Retrofit>(named(SHARED_RETROFIT))
        )
    }
}

fun getRepositoryService(
    retrofit: Retrofit
): RepositoryCatalogService = retrofit
    .create(RepositoryCatalogService::class.java)
