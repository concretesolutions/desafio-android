package com.desafioandroid.core.service

import com.desafioandroid.BuildConfig
import com.desafioandroid.MyApplication.Companion.applicationContext
import com.example.recruiting.core.api.ApiService
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    private val cacheSize = (10 * 1024 * 1024).toLong()
    private val context = applicationContext()

    private val networkCacheInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())

        val cacheControl = CacheControl.Builder()
            .maxAge(1, TimeUnit.MINUTES)
            .build()

        response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .cache(Cache(context.cacheDir, cacheSize))
        .addInterceptor(loggingInterceptor)
        .addNetworkInterceptor(networkCacheInterceptor)
        .build()


    private val getRetrofitInstance = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_API)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()


    val apiService : ApiService = getRetrofitInstance.create(ApiService::class.java)
}

