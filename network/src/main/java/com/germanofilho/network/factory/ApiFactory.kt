package com.germanofilho.network.factory

import com.germanofilho.network.BuildConfig
import com.germanofilho.network.config.AppConfig
import com.germanofilho.network.interceptor.networkCacheInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {

    private const val cacheSize = (50 * 1024 * 1024).toLong()

    private val okHttpClient = OkHttpClient.Builder()
        .cache(Cache(AppConfig.appContext.cacheDir, cacheSize))
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addNetworkInterceptor(networkCacheInterceptor)
        .build()

    fun <S> request(api : Class<S>) : S {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(api)
    }
}