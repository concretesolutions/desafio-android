package com.jsouza.repocatalog.di

import androidx.room.Room
import com.jsouza.repocatalog.data.RepoRepositoryImpl
import com.jsouza.repocatalog.data.local.RepoDatabase
import com.jsouza.repocatalog.data.remote.RepoCatalogService
import com.jsouza.repocatalog.domain.repository.RepoRepository
import com.jsouza.repocatalog.domain.usecase.RefreshPaginatedData
import com.jsouza.repocatalog.presentation.RepoCatalogViewModel
import com.jsouza.repocatalog.presentation.adapter.RepoCatalogAdapter
import com.jsouza.shared_components.di.SHARED_RETROFIT
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

private const val repositoryDatabase = "REPOSITORY_DATABASE"
private const val repositoryDao = "REPOSITORY_DAO"
private const val keysDao = "REPOSITORY_KEYS_DAO"

@ExperimentalCoroutinesApi
@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val repositoryCatalogModule = module {

    viewModel {
        RepoCatalogViewModel(
            get<RefreshPaginatedData>()
        )
    }

    factory {
            (startRepoDetail: (
                String?,
                String?,
                Long?
            ) -> Unit) ->
        RepoCatalogAdapter(startRepoDetail)
    }

    single {
        RepoRepositoryImpl(
            get<RepoCatalogService>(),
            get<RepoDatabase>(named(repositoryDatabase))
        ) as RepoRepository
    }

    single {
        RefreshPaginatedData(
            get<RepoRepository>()
        )
    }

    single {
        getRepositoryService(
            get<Retrofit>(named(SHARED_RETROFIT))
        )
    }

    single(named(repositoryDatabase)) {
        Room.databaseBuilder(
            androidContext(),
            RepoDatabase::class.java,
            "pulls.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    single(named(repositoryDao)) {
        get<RepoDatabase>(named(repositoryDatabase)).reposDao()
    }

    single(named(keysDao)) {
        get<RepoDatabase>(named(repositoryDatabase)).keysDao()
    }
}

private fun getRepositoryService(
    retrofit: Retrofit
): RepoCatalogService = retrofit
    .create(RepoCatalogService::class.java)
