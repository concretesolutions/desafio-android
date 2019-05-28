package com.jmc.desafioandroidkotlin.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.room.Room
import com.jmc.desafioandroidkotlin.data.dataSource.factory.GitHubDataFactory
import com.jmc.desafioandroidkotlin.data.dataSource.factory.GitHubDataStoreFactory
import com.jmc.desafioandroidkotlin.data.dataSource.local.GitHubCacheDataStore
import com.jmc.desafioandroidkotlin.data.dataSource.local.GitHubDatabase
import com.jmc.desafioandroidkotlin.data.dataSource.remote.GitHubApi
import com.jmc.desafioandroidkotlin.data.dataSource.remote.GitHubRemoteDataStore
import com.jmc.desafioandroidkotlin.domain.repository.GithubRepository
import com.jmc.desafioandroidkotlin.domain.usecase.FetchRepositoriesUseCase
import com.jmc.desafioandroidkotlin.domain.usecase.GetPullsUseCase
import com.jmc.desafioandroidkotlin.presentation.model.RepositoryItem
import com.jmc.desafioandroidkotlin.presentation.viewModels.MainViewModel
import com.jmc.desafioandroidkotlin.presentation.viewModels.PullsViewModel
import com.jmc.desafioandroidkotlin.presentation.ui.adapters.PagedRepositoryAdapter
import com.jmc.desafioandroidkotlin.presentation.ui.adapters.PullAdapter
import com.jmc.desafioandroidkotlin.presentation.ui.pagging.RepositoryBoundaryCallback
import com.jmc.desafioandroidkotlin.utils.*

import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module
import org.koin.experimental.builder.factory
import org.koin.experimental.builder.factoryBy
import org.koin.experimental.builder.single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    /* Android Services */

    single {
        androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    /* Retrofit */

    single {
        OkHttpClient.Builder()
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>()
            .create(GitHubApi::class.java) as GitHubApi
    }

    /* Database */

    single {
        Room.databaseBuilder(
            androidContext(),
            GitHubDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    /* Paging */

    single {
        get<GitHubDatabase>()
            .repositories
            .browse()
            .map { it.toRepositoryItem() }
    }

    single {
        PagedList.Config.Builder()
            .setPageSize(SIZE_PAGE)
            .build()
    }

    single {
        LivePagedListBuilder(get<DataSource.Factory<Int, RepositoryItem>>(), get<PagedList.Config>())
            .setBoundaryCallback(get<RepositoryBoundaryCallback>())
            .setInitialLoadKey(1)
            .build()
    }

    single<RepositoryBoundaryCallback>()

    factory<LiveCompletable>()


    /* View models */

    viewModel<MainViewModel>()
    viewModel<PullsViewModel>()

    /* Use cases */

    factory<FetchRepositoriesUseCase>()
    factory<GetPullsUseCase>()

    /* Repositories */

    factoryBy<GithubRepository, GitHubDataFactory>()

    /* Data stores */

    factory<GitHubCacheDataStore>()
    factory<GitHubRemoteDataStore>()

    /* Factory */

    factory<GitHubDataStoreFactory>()

    /* Adapters */

    factory { (callback: PagedRepositoryAdapter.AdapterCallback) ->
        PagedRepositoryAdapter(callback)
    }

    factory { (callback: PullAdapter.AdapterCallback) ->
        PullAdapter(callback)
    }
}