package com.dobler.desafio_android.di

import android.util.Log
import com.dobler.desafio_android.BuildConfig
import com.dobler.desafio_android.data.api.githubRepository.GithubRepositoryService
import com.dobler.desafio_android.data.api.repositoryPullRequest.RepositoryPullRequestService
import com.dobler.desafio_android.data.repository.githubRepository.GithubRepositoryDataSource
import com.dobler.desafio_android.data.repository.githubRepository.Repository
import com.dobler.desafio_android.data.repository.pullRequest.PullRequestRepository
import com.dobler.desafio_android.ui.pull.PullRequestViewModel
import com.dobler.desafio_android.ui.repository.ListRepositoryViewModel
import com.dobler.desafio_android.util.rx.SchedulerContract
import com.dobler.desafio_android.util.rx.SchedulerProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object AppModule {


    val apiModule = module {

        single {
            initRetrofit()
        }

        single {
            get<Retrofit>().create(RepositoryPullRequestService::class.java) as RepositoryPullRequestService
        }

        single {
            get<Retrofit>().create(GithubRepositoryService::class.java) as GithubRepositoryService
        }

    }

    val repositoriesModule = module {

        single { GithubRepositoryDataSource(get()) }
        single { Repository(get()) }
        single { PullRequestRepository(get()) }
    }

    val rxModule = module {

        single {
            SchedulerProvider() as SchedulerContract
        }

    }

    val vieModelModule = module {

        viewModel { ListRepositoryViewModel(get(), get()) }
        viewModel { PullRequestViewModel(get(), get()) }

    }

    private fun initRetrofit(): Retrofit {

        val httpBuilder = OkHttpClient.Builder()

        httpBuilder.readTimeout(15, TimeUnit.SECONDS)
        httpBuilder.connectTimeout(15, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                Log.d("RETROFIT", message)
            })
            logging.level = HttpLoggingInterceptor.Level.BODY

            httpBuilder.addInterceptor(logging)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(httpBuilder.build())

            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())


        return retrofit.build()

    }


}