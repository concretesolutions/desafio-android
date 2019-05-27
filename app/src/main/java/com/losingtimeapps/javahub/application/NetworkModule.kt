package com.losingtimeapps.javahub.application

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapterFactory
import com.losingtimeapps.data.remote.GitHubService
import com.losingtimeapps.domain.boundary.GitHubRepository
import com.losingtimeapps.javahub.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
internal class NetworkModule {

    @Provides
    fun provideService(retrofit: Retrofit): GitHubService = retrofit.create(GitHubService::class.java)

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        @Named("API_URL") baseUrl: String, gson: Gson, okHttpClient: OkHttpClient
    ) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    @Provides
    @Named("API_URL")
    fun provideBaseUrl(): String {
        return BuildConfig.API_URL
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideApiOkHttpClient(
    ): OkHttpClient = OkHttpClient().newBuilder().build()
}
