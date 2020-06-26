package com.germanofilho.pullrequest.di

import com.germanofilho.pullrequest.presentation.viewmodel.PullRequestViewModel
import com.germanofilho.pullrequest.repository.PullRequestRepository
import com.germanofilho.pullrequest.repository.PullRequestRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PullRequestViewModel(get()) }
}

val repositoryModule = module {
    single<PullRequestRepository> { PullRequestRepositoryImpl() }
}

object PullRequestModule : KoinComponent {
    fun inject() =
        loadKoinModules(
            listOf(
                viewModelModule,
                repositoryModule
            )
        )
}