package com.example.desafioandroidapp.data

import com.example.desafioandroidapp.data.dto.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DesafioApiService {

    @GET("/search/repositories?q=language:Java&sort=stars")
    fun getRepositories(@Query("page") page: Int): Call<Repository>
}