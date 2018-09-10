package com.concrete.desafioandroid.di

import com.concrete.desafioandroid.data.repositories.ReposRepository
import com.concrete.desafioandroid.data.source.datasource.DataSource
import com.concrete.desafioandroid.data.source.remote.network.NetworkClient
import com.concrete.desafioandroid.data.source.remote.network.RestApi
import com.concrete.desafioandroid.data.source.remote.network.RemoteDataSource
import com.concrete.desafioandroid.features.pulls.PullsInteractor
import com.concrete.desafioandroid.features.pulls.PullsPresenter
import com.concrete.desafioandroid.features.repos.ReposPresenter
import com.concrete.desafioandroid.features.repos.ReposInteractor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

const val REMOTE = "remote"

val networkModule = Kodein.Module("Network") {
    bind<RestApi>() with singleton { NetworkClient().getApiClient() }
}

val dataSourceModule = Kodein.Module("DataSource") {
    bind<DataSource>(tag = REMOTE) with singleton { RemoteDataSource(instance()) }
}

val repositoryModule = Kodein.Module("Repository") {
    bind<ReposRepository>() with singleton { ReposRepository(instance(REMOTE)) }
}

val interactorModule = Kodein.Module("Interactor") {
    bind<ReposInteractor>() with provider { ReposInteractor(instance()) }
    bind<PullsInteractor>() with provider { PullsInteractor(instance()) }
}

val presenterModule = Kodein.Module("Presenter") {
    bind<ReposPresenter>() with provider { ReposPresenter(instance()) }
    bind<PullsPresenter>() with provider { PullsPresenter(instance()) }
}
