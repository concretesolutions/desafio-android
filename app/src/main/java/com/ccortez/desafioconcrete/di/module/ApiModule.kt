package com.ccortez.desafioconcrete.di.module

import com.ccortez.desafioconcrete.data.remote.ApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): ApiService = Retrofit.Builder()
        .baseUrl(ApiService.HTTP_API_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(provideHttpInterceptor().build())
        .build()
        .create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideHttpInterceptor(): OkHttpClient.Builder {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
        httpClientBuilder.retryOnConnectionFailure(true)
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
//        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        httpClientBuilder.addInterceptor(httpLoggingInterceptor)
            .connectionSpecs(
                Arrays.asList(
                    ConnectionSpec.CLEARTEXT,
                    ConnectionSpec.MODERN_TLS,
                    ConnectionSpec.COMPATIBLE_TLS
                )
            )
        return httpClientBuilder
    }

    companion object {

        var TIMEOUT_CONNECT: Long = 30L
        var TIMEOUT_READ: Long = 30L

    }
}
