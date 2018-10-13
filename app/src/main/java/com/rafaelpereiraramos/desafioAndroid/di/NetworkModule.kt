package com.rafaelpereiraramos.desafioAndroid.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rafaelpereiraramos.desafioAndroid.App
import com.rafaelpereiraramos.desafioAndroid.api.GithubService
import dagger.Module
import dagger.Provides
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
    fun provideNetworkExecutor(): Executor {
        return Executors.newFixedThreadPool(5)
    }

    @Provides
    @Singleton
    fun provideGithubService(gson: Gson): GithubService {
        val builder = Retrofit.Builder()
                .baseUrl(App.URL_BASE)
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
}