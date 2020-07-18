package com.jsouza.repodetail.di

import com.jsouza.repodetail.data.RepoDetailService
import com.jsouza.repodetail.presentation.RepoDetailViewModel
import com.jsouza.shared_components.di.SHARED_RETROFIT
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val repositoryDetailModule = module {

    viewModel {
        RepoDetailViewModel(
            get<RepoDetailService>()
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
): RepoDetailService = retrofit
    .create(RepoDetailService::class.java)
