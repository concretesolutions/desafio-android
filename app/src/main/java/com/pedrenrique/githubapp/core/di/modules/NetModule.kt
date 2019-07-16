package com.pedrenrique.githubapp.core.di.modules

import android.content.Context
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pedrenrique.githubapp.BuildConfig
import com.pedrenrique.githubapp.core.di.API_BASE_URL
import com.pedrenrique.githubapp.core.di.ENABLE_HTTP_LOG
import com.pedrenrique.githubapp.core.net.services.GithubService
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val netModule = module {
    factory<GithubService> {
        get<Retrofit>().create(GithubService::class.java)
    }

    factory {
        Retrofit.Builder()
            .baseUrl(getProperty<String>(API_BASE_URL))
            .addConverterFactory(GsonConverterFactory.create(get<Gson>()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(get<OkHttpClient.Builder>().build())
            .build()
    }

    /**
     * Provide OkHttpClient.Builder not authenticated
     */
    factory<OkHttpClient.Builder> {
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .cache(get<Cache>())
            .apply {
                get<Set<Interceptor>>().forEach { addInterceptor(it) }
            }
    }

    /**
     * Provide Interceptors
     */
    factory<Set<Interceptor>> {
        setOf(
            get<HttpLoggingInterceptor>()
        )
    }

    /**
     * Provide LoggingInterceptor
     */
    factory {
        HttpLoggingInterceptor().apply {
            level = if (getProperty(ENABLE_HTTP_LOG))
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
    }

    /**
     * Provide GSON
     */
    factory<Gson> {
        GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .disableHtmlEscaping()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    /**
     * Provide cache
     */
    factory { Cache(get<Context>().cacheDir, (10 * 1024 * 1024).toLong()) }
}