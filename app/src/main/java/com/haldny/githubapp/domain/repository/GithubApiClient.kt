package com.haldny.githubapp.domain.repository

import com.haldny.githubapp.GithubApplication.Companion.applicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubApiClient {

    private val cacheSize = (30 * 1024 * 1024).toLong()
    private val context = applicationContext()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .cache(Cache(context.cacheDir, cacheSize))
        .addInterceptor(loggingInterceptor)
        .build()


    private val getRetrofitInstance = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()


    val githubApi : GithubApi = getRetrofitInstance.create(GithubApi::class.java)
}
