package com.silvioapps.githubkotlin.features.shared.services

import com.google.gson.Gson
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {
    companion object{
        fun <T> createService(apiBaseUrl : String, timeout : Long, serviceClass : Class<T>) : T {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val httpClient = OkHttpClient.Builder().readTimeout(timeout, TimeUnit.SECONDS)

            httpClient?.addInterceptor(loggingInterceptor)

            val retrofit = Retrofit.Builder()
                    .baseUrl(apiBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create(Gson()))
                    .client(httpClient.build())
                    .build()

            return retrofit.create(serviceClass)
        }
    }
}
