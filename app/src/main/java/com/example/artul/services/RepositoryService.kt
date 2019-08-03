package com.example.artul.services

import com.example.artul.models.Generic
import com.example.artul.models.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepositoryService {

    @GET("search/repositories?q=language:Java&sort=stars")

    fun list(@Query("page") page: Int): Call<Generic>

}