package com.hako.githubapi.data.retrofit

import com.hako.githubapi.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class GithubClient {

    private val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
        Timber.d(it)
    }).setLevel(getLoggerLevel())

    private val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()

    fun getClient(): GithubApi = retrofit.create(GithubApi::class.java)

    private fun getLoggerLevel() = when (BuildConfig.DEBUG) {
        true -> HttpLoggingInterceptor.Level.BASIC
        false -> HttpLoggingInterceptor.Level.NONE
    }
}
