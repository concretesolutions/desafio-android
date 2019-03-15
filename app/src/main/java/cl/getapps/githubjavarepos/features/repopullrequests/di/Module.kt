package cl.getapps.githubjavarepos.features.repopullrequests.di

import cl.getapps.githubjavarepos.BuildConfig
import cl.getapps.githubjavarepos.core.data.CacheSource
import cl.getapps.githubjavarepos.core.data.RemoteSource
import cl.getapps.githubjavarepos.core.remote.ServiceFactory
import cl.getapps.githubjavarepos.features.repopullrequests.data.cache.PullRequestCacheDataSource
import cl.getapps.githubjavarepos.features.repopullrequests.data.datasource.PullRequestsDataSourceFactory
import cl.getapps.githubjavarepos.features.repopullrequests.data.remote.PullRequestsRemoteDataSource
import cl.getapps.githubjavarepos.features.repopullrequests.data.repository.PullRequestsDataRepository
import cl.getapps.githubjavarepos.features.repopullrequests.domain.repository.PullRequestsRepository
import cl.getapps.githubjavarepos.features.repopullrequests.domain.usecase.GetPullRequests
import cl.getapps.githubjavarepos.features.repopullrequests.ui.PullRequestsRecyclerViewAdapter
import cl.getapps.githubjavarepos.features.repopullrequests.ui.PullRequestsViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val pullRequestsModule = module("PullRequests", override = true) {
    factory { ServiceFactory.makePullRequestsService(BuildConfig.DEBUG) }

    factory { PullRequestsRemoteDataSource(get()) as RemoteSource }
    factory { PullRequestCacheDataSource() as CacheSource }

    factory { PullRequestsDataSourceFactory(get(), get()) }

    single { PullRequestsDataRepository(get()) as PullRequestsRepository }

    factory { GetPullRequests(get(), get(), get()) }

    viewModel { PullRequestsViewModel(get()) }

    factory { PullRequestsRecyclerViewAdapter() }
}
