package com.jsouza.repopullrequests.di

import androidx.room.Room
import com.jsouza.repopullrequests.data.PullsRepositoryImpl
import com.jsouza.repopullrequests.data.local.PullsDatabase
import com.jsouza.repopullrequests.data.local.dao.PullsDao
import com.jsouza.repopullrequests.data.remote.PullRequestsService
import com.jsouza.repopullrequests.domain.repository.PullsRepository
import com.jsouza.repopullrequests.domain.usecase.FetchPullRequestsFromApi
import com.jsouza.repopullrequests.domain.usecase.GetPullRequestsFromDatabase
import com.jsouza.repopullrequests.presentation.PullRequestsViewModel
import com.jsouza.repopullrequests.presentation.adapter.PullRequestsAdapter
import com.jsouza.repopullrequests.utils.Constants.Companion.DATABASE_NAME
import com.jsouza.shared_components.di.SHARED_RETROFIT
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
val pullRequestsModule = module {

    viewModel {
        PullRequestsViewModel(
            get<CoroutineDispatcher>(),
            get<FetchPullRequestsFromApi>(),
            get<GetPullRequestsFromDatabase>()
        )
    }

    factory {
        PullRequestsAdapter()
    }

    factory {
        PullsRepositoryImpl(
            get<PullRequestsService>(),
            get<PullsDao>(named(pullsDao))
        ) as PullsRepository
    }

    factory {
        getRepositoryService(
            get<Retrofit>(named(SHARED_RETROFIT))
        )
    }

    factory {
        FetchPullRequestsFromApi(
            get<PullsRepository>()
        )
    }

    factory {
        GetPullRequestsFromDatabase(
            get<PullsRepository>()
        )
    }

    single(named(pullsDatabase)) {
        Room.databaseBuilder(
            androidContext(),
            PullsDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    factory {
        getCoroutinesDispatchersIo()
    }

    single(named(pullsDao)) {
        get<PullsDatabase>(named(pullsDatabase)).pullsDao()
    }
}

fun getRepositoryService(
    retrofit: Retrofit
): PullRequestsService = retrofit
    .create(PullRequestsService::class.java)

private fun getCoroutinesDispatchersIo() = Dispatchers.IO
