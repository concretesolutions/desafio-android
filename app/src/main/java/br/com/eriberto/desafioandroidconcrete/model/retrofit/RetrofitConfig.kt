package br.com.eriberto.desafioandroidconcrete.model.retrofit

import br.com.eriberto.desafioandroidconcrete.model.retrofit.services.RepositoriosService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    var interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    var client = OkHttpClient.Builder().addInterceptor(interceptor).build()!!

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/search/")
        .client(client)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun repositoriosService() = retrofit.create(RepositoriosService::class.java)
}