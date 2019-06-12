package com.abs.javarepos.model.githubapi

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object GithubApi {

    private const val HOST_GITHUB_API = "https://api.github.com"
    val endpoints: Endpoints

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder()
            .setPrettyPrinting()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(HOST_GITHUB_API)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        endpoints = retrofit.create(Endpoints::class.java)
    }
}