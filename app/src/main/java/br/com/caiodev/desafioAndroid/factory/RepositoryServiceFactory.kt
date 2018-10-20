package br.com.caiodev.desafioAndroid.factory

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RepositoryServiceFactory {

    inline fun <reified T> createService(): T {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createAndDeliverHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(T::class.java)
    }

    fun createAndDeliverHttpClient(): OkHttpClient {

        val okHttpClientCreator = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        okHttpClientCreator.addInterceptor(loggingInterceptor)
        okHttpClientCreator.connectTimeout(1L, TimeUnit.MINUTES)
        okHttpClientCreator.readTimeout(1L, TimeUnit.MINUTES)
        okHttpClientCreator.writeTimeout(1L, TimeUnit.MINUTES)

        okHttpClientCreator.hostnameVerifier { _, _ -> true }

        return okHttpClientCreator.build()
    }

    companion object {
        const val baseUrl = "https://api.github.com/"
    }
}