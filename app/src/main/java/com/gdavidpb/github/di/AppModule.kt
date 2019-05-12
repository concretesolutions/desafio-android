package com.gdavidpb.github.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.room.Room
import com.gdavidpb.github.data.source.GitHubDataRepository
import com.gdavidpb.github.data.source.GitHubDataStoreFactory
import com.gdavidpb.github.data.source.local.GitHubCacheDataStore
import com.gdavidpb.github.data.source.local.GitHubDatabase
import com.gdavidpb.github.data.source.remote.GitHubApi
import com.gdavidpb.github.data.source.remote.GitHubRemoteDataStore
import com.gdavidpb.github.domain.repository.VCSRepository
import com.gdavidpb.github.domain.usecase.FetchRepositoriesUseCase
import com.gdavidpb.github.domain.usecase.GetPullsUseCase
import com.gdavidpb.github.presentation.model.RepositoryItem
import com.gdavidpb.github.presentation.viewModels.MainViewModel
import com.gdavidpb.github.presentation.viewModels.PullsViewModel
import com.gdavidpb.github.ui.adapters.PagedRepositoryAdapter
import com.gdavidpb.github.ui.adapters.PullAdapter
import com.gdavidpb.github.ui.pagging.RepositoryBoundaryCallback
import com.gdavidpb.github.utils.*
import com.squareup.picasso.Picasso
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
            .baseUrl(URL_BASE_GITHUB_API)
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
            .setPageSize(PAGE_SIZE)
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

    /* Picasso */

    single {
        Picasso.get()
    }

    /* View models */

    viewModel<MainViewModel>()
    viewModel<PullsViewModel>()

    /* Use cases */

    factory<FetchRepositoriesUseCase>()
    factory<GetPullsUseCase>()

    /* Repositories */

    factoryBy<VCSRepository, GitHubDataRepository>()

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