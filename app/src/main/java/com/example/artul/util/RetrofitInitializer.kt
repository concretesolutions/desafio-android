package com.example.artul.util

import com.example.artul.services.PullService
import com.example.artul.services.RepositoryService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    fun repositoryService() : RepositoryService = retrofit.create(RepositoryService::class.java)
    fun pullService() : PullService = retrofit.create(PullService::class.java)

}