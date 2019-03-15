package cl.getapps.githubjavarepos.features.repos.di

import cl.getapps.githubjavarepos.BuildConfig
import cl.getapps.githubjavarepos.core.data.CacheSource
import cl.getapps.githubjavarepos.core.data.RemoteSource
import cl.getapps.githubjavarepos.core.remote.ServiceFactory
import cl.getapps.githubjavarepos.features.repos.data.cache.ReposCacheDataSource
import cl.getapps.githubjavarepos.features.repos.data.remote.ReposRemoteDataSource
import cl.getapps.githubjavarepos.features.repos.data.repository.ReposDataRepository
import cl.getapps.githubjavarepos.features.repos.data.source.ReposDataSourceFactory
import cl.getapps.githubjavarepos.features.repos.domain.repository.ReposRepository
import cl.getapps.githubjavarepos.features.repos.domain.usecase.GetRepos
import cl.getapps.githubjavarepos.features.repos.ui.ReposRecyclerViewAdapter
import cl.getapps.githubjavarepos.features.repos.ui.ReposViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val reposModule = module("Repos", override = true) {
    factory { ServiceFactory.makeRepoService(BuildConfig.DEBUG) }

    factory { ReposRemoteDataSource(get()) as RemoteSource }
    factory { ReposCacheDataSource() as CacheSource }

    factory { ReposDataSourceFactory(get(), get()) }

    single { ReposDataRepository(get()) as ReposRepository }

    factory { GetRepos(get(), get(), get()) }

    viewModel { ReposViewModel(get()) }

    factory { ReposRecyclerViewAdapter() }
}
