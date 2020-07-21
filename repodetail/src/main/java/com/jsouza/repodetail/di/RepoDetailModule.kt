package com.jsouza.repodetail.di

import androidx.room.Room
import com.jsouza.repodetail.data.PullsRepositoryImpl
import com.jsouza.repodetail.data.RepoDetailService
import com.jsouza.repodetail.data.local.PullsDatabase
import com.jsouza.repodetail.data.local.dao.PullsDao
import com.jsouza.repodetail.domain.repository.PullsRepository
import com.jsouza.repodetail.domain.usecase.FetchPullRequestsFromApi
import com.jsouza.repodetail.domain.usecase.GetPullRequestsFromDatabase
import com.jsouza.repodetail.presentation.RepoDetailViewModel
import com.jsouza.repodetail.presentation.adapter.RepoDetailAdapter
import com.jsouza.shared_components.di.SHARED_RETROFIT
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

private const val pullsDatabase = "PULLS_DATABASE"
private const val pullsDao = "PULLS_DAO"

@ExperimentalCoroutinesApi
@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val repositoryDetailModule = module {

    viewModel {
        RepoDetailViewModel(
            get<FetchPullRequestsFromApi>(),
            get<GetPullRequestsFromDatabase>()
        )
    }

    single {
        RepoDetailAdapter()
    }

    single {
        PullsRepositoryImpl(
            get<RepoDetailService>(),
            get<PullsDao>(named(pullsDao))
        ) as PullsRepository
    }

    single {
        getRepositoryService(
            get<Retrofit>(named(SHARED_RETROFIT))
        )
    }

    single {
        FetchPullRequestsFromApi(
            get<PullsRepository>()
        )
    }

    single {
        GetPullRequestsFromDatabase(
            get<PullsRepository>()
        )
    }

    single(named(pullsDatabase)) {
        Room.databaseBuilder(
            androidContext(),
            PullsDatabase::class.java,
            "pullRequests.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    single(named(pullsDao)) {
        get<PullsDatabase>(named(pullsDatabase)).pullsDao()
    }
}

fun getRepositoryService(
    retrofit: Retrofit
): RepoDetailService = retrofit
    .create(RepoDetailService::class.java)
