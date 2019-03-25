package dev.renatoneto.githubrepos.di

import android.util.Log
import dev.renatoneto.githubrepos.BuildConfig
import dev.renatoneto.githubrepos.model.github.GithubRepository
import dev.renatoneto.githubrepos.network.github.GithubApi
import dev.renatoneto.githubrepos.network.github.GithubDataSource
import dev.renatoneto.githubrepos.network.github.GithubNetworkService
import dev.renatoneto.githubrepos.ui.repositorydetails.RepositoryDetailsViewModel
import dev.renatoneto.githubrepos.ui.repositorylist.RepositoryListViewModel
import dev.renatoneto.githubrepos.util.rx.SchedulerContract
import dev.renatoneto.githubrepos.util.rx.SchedulerProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object AppModule {

    var apiUrl = "https://api.github.com"

    val apiModule = module {

        single {
            initRetrofit()
        }

        single {
            get<Retrofit>().create(GithubApi::class.java) as GithubApi
        }

    }

    val dataSourceModule = module {

        single { GithubNetworkService(get()) as GithubDataSource }

    }

    val rxModule = module {

        single {
            SchedulerProvider() as SchedulerContract
        }

    }

    val appModule = module {

        viewModel { RepositoryListViewModel(get(), get()) }

        viewModel { (githubRepository: GithubRepository) ->
            RepositoryDetailsViewModel(get(), githubRepository, get())
        }

    }

    private fun initRetrofit(): Retrofit {

        val httpBuilder = OkHttpClient.Builder()

        httpBuilder.readTimeout(15, TimeUnit.SECONDS)
        httpBuilder.connectTimeout(15, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                Log.d("HTTP", message)
            })
            logging.level = HttpLoggingInterceptor.Level.BODY

            httpBuilder.addInterceptor(logging)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(httpBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

        return retrofit.build()

    }

}