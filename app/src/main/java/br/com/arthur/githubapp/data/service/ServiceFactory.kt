package br.com.arthur.githubapp.data.service

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceFactory {

    companion object {
        private const val maxSecondsToRequest: Long = 5

        private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        private val httpClient = OkHttpClient.Builder()
            .readTimeout(maxSecondsToRequest, TimeUnit.SECONDS)
            .connectTimeout(maxSecondsToRequest, TimeUnit.SECONDS)
            .writeTimeout(maxSecondsToRequest, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request()
                val builder = request.newBuilder()
                    .addHeader("Content-Type", "application/json")
                chain.proceed(builder.build())
            }.addInterceptor(loggingInterceptor)
            .build()

        private val gson = GsonBuilder()
            .setLenient()
            .create()

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClient)
            .build()

        fun getService(): Service = retrofit.create(
            Service::class.java
        )
    }

}