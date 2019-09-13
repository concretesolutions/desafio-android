package com.example.recruiting.core.api

import com.example.recruiting.homelist.model.domain.entity.HomeResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun getListHome(@Query("page") page: Int): HomeResponse
}
