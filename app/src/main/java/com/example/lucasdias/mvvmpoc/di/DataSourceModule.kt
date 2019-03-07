package com.example.lucasdias.mvvmpoc.di

import com.example.lucasdias.mvvmpoc.data.service.PullRequestService
import com.example.lucasdias.mvvmpoc.data.service.RepositoryService
import com.example.lucasdias.mvvmpoc.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val serviceModule = module {
    single { createOkHttpClient() }
    single { createRetrofit(get())}
    single { getRepositoryService(get())}
    single { getPullRequestService(get())}
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.GITHUB_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

fun getRepositoryService(retrofit: Retrofit): RepositoryService = retrofit.create(RepositoryService::class.java)

fun getPullRequestService(retrofit: Retrofit): PullRequestService = retrofit.create(PullRequestService::class.java)