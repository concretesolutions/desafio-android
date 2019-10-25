package com.example.eloyvitorio.githubjavapop.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateRetrofitImpl : CreateRetrofit {
    override fun getApi() = apiInstance

    companion object {
        private lateinit var apiInstance: GitHubService

        fun createApi(url: String) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            apiInstance = retrofit.create(GitHubService::class.java)
        }
    }
}

interface CreateRetrofit {
    fun getApi(): GitHubService
}