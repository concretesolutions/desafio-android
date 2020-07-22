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
            get<FetchPullRequestsFromApi>(),
            get<GetPullRequestsFromDatabase>()
        )
    }

    single {
        PullRequestsAdapter()
    }

    single {
        PullsRepositoryImpl(
            get<PullRequestsService>(),
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
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    single(named(pullsDao)) {
        get<PullsDatabase>(named(pullsDatabase)).pullsDao()
    }
}

fun getRepositoryService(
    retrofit: Retrofit
): PullRequestsService = retrofit
    .create(PullRequestsService::class.java)
