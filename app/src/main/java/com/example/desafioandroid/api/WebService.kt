package com.example.desafioandroid.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val retrofitClient: ApiInterface by lazy { retrofit.create(ApiInterface::class.java) }

private val retrofit: Retrofit by lazy { Retrofit.Builder()
    .baseUrl("https://api.github.com")
    .client(client)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()
}

private val client: OkHttpClient get() = OkHttpClient().newBuilder().build()

