package com.rafaelpereiraramos.desafioAndroid.di

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rafaelpereiraramos.desafioAndroid.App
import com.rafaelpereiraramos.desafioAndroid.api.GithubService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

/**
 * Created by Rafael P. Ramos on 13/10/2018.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named("networkExecutor")
    fun provideNetworkExecutor(): Executor {
        return Executors.newFixedThreadPool(5)
    }

    @Provides
    @Singleton
    fun provideGithubService(gson: Gson, httpClient: OkHttpClient): GithubService {
        val builder = Retrofit.Builder()
                .baseUrl(App.URL_BASE)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return builder.create(GithubService::class.java)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
                .setDateFormat(App.DATE_FORMAT)
                .create()
    }

    @Provides
    @Singleton
    fun provideOkkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.d("API", it)
        })
        logger.level = HttpLoggingInterceptor.Level.BASIC

        return OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
    }
}