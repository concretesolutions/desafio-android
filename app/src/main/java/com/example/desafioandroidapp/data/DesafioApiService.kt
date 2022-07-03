package com.example.desafioandroidapp.data

import com.example.desafioandroidapp.data.dto.Repository
import retrofit2.Call
import retrofit2.http.GET

interface DesafioApiService {

    @GET("/search/repositories?q=language:Java&sort=stars&page=1")
    fun getRepositories(): Call<Repository>
}