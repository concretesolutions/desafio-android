package br.com.desafio.concrete.network

import br.com.desafio.concrete.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Malkes on 25/09/2018.
 */

class ServiceFactory {
    companion object {
        const val CONNECTION_TIMEOUT = 120000L
        const val WRITE_TIMEOUT = 120000L
        const val READ_TIMEOUT = 120000L
    }


    fun getRetrofit(): WSInterface {

        return createRetrofit(createOkHttpClient()).create(WSInterface::class.java)
    }


    private fun createOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        if(BuildConfig.DEBUG){
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(httpLoggingInterceptor).build()
    }

    private fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }
}