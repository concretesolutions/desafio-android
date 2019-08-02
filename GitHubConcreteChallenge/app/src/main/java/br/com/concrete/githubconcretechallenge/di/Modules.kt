package br.com.concrete.githubconcretechallenge.di

import android.content.Context
import br.com.concrete.githubconcretechallenge.BuildConfig
import br.com.concrete.githubconcretechallenge.cache.CacheProviders
import br.com.concrete.githubconcretechallenge.features.pullrequests.datasource.PullRequestsDataSource
import br.com.concrete.githubconcretechallenge.features.pullrequests.datasource.PullRequestsRemoteDataSource
import br.com.concrete.githubconcretechallenge.features.pullrequests.service.PullRequestsRetrofit
import br.com.concrete.githubconcretechallenge.features.pullrequests.view.PullRequestsAdapter
import br.com.concrete.githubconcretechallenge.features.pullrequests.viewmodel.PullRequestsViewModel
import br.com.concrete.githubconcretechallenge.features.repositories.datasource.RepositoriesListDataSourceFactory
import br.com.concrete.githubconcretechallenge.features.repositories.datasource.RepositoriesListRemoteDataSource
import br.com.concrete.githubconcretechallenge.features.repositories.service.RepositoriesListRetrofit
import br.com.concrete.githubconcretechallenge.features.repositories.view.RepositoriesPagedAdapter
import br.com.concrete.githubconcretechallenge.features.repositories.viewmodel.RepositoriesListViewModel
import io.rx_cache2.internal.RxCache
import io.victoralbertos.jolyglot.GsonSpeaker
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val applicationModule = module {
    factory { RepositoriesPagedAdapter() }
    factory { PullRequestsAdapter() }
    factory { RepositoriesListDataSourceFactory(get()) }

    viewModel { RepositoriesListViewModel(get()) }
    viewModel { PullRequestsViewModel(get()) }

    single {
        RepositoriesListRemoteDataSource(
            get(),
            getOrNull()
        )
    }

    single<PullRequestsDataSource> {
        PullRequestsRemoteDataSource(
            get(),
            getOrNull()
        )
    }

}

val retrofitModule = module {
    single { provideOkHttpClient() }

    single<RepositoriesListRetrofit> { provideRetrofit(get()) }
    single<PullRequestsRetrofit> { provideRetrofit(get()) }
}

val cacheModule = module {
    single { provideCache(get()) }
    single { provideCacheProviders(get()) }
}

fun provideCache(context: Context) : RxCache {
    return RxCache.Builder()
        .setMaxMBPersistenceCache(10)
        .persistence(context.filesDir, GsonSpeaker())
}

fun provideCacheProviders(rxCache: RxCache): CacheProviders {
    return rxCache.using(CacheProviders::class.java)
}


fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

inline fun <reified T> provideRetrofit(okHttpClient: OkHttpClient): T {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(T::class.java)
}
