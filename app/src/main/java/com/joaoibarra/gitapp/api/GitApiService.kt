package com.joaoibarra.gitapp.api

import com.google.gson.GsonBuilder
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object GitApiService {
    fun create(): GitApi{
        val builder = OkHttpClient.Builder()

        val gsonBuilder = GsonBuilder().disableHtmlEscaping().create()

        // Logging
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.interceptors().add(interceptor)

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                        GsonConverterFactory.create(gsonBuilder))
                .baseUrl("https://api.github.com/")
                .client(builder.build())
                .build()
        return retrofit.create(GitApi::class.java)
    }

    private fun getHeader(): Headers {
        val builder = Headers.Builder()
        builder.add("Accept", "application/vnd.github.v3.text-match+json")
        //builder.add("Content-Type", "application/json")
        return builder.build()
    }
}