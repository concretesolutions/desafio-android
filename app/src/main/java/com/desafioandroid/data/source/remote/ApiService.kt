package com.desafioandroid.data.source.remote

import com.desafioandroid.data.model.home.entity.HomeResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun getListHome(@Query("page") page: Int): HomeResponse
}
