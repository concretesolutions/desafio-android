package com.jsouza.repocatalog.di

import com.jsouza.repocatalog.data.repocatalog.CatalogRepositoryImpl
import com.jsouza.repocatalog.data.repocatalog.remote.RepositoryCatalogService
import com.jsouza.repocatalog.domain.repository.CatalogRepository
import com.jsouza.repocatalog.domain.usecase.FetchRepositoriesFromApi
import com.jsouza.repocatalog.presentation.RepositoryCatalogViewModel
import com.jsouza.shared_components.di.SHARED_RETROFIT
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryCatalogModule = module {

    viewModel {
        RepositoryCatalogViewModel(
            get<FetchRepositoriesFromApi>()
        )
    }

    single {
        FetchRepositoriesFromApi(
            get<CatalogRepository>()
        )
    }

    factory {
        CatalogRepositoryImpl(
            repositoryCatalogService = get<RepositoryCatalogService>()
        ) as CatalogRepository
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
