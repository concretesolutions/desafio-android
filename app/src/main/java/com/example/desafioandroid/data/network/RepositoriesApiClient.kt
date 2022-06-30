package com.example.desafioandroid.data.network

import com.example.desafioandroid.data.model.RepositoriesModel
import retrofit2.Response
import retrofit2.http.GET

interface RepositoriesApiClient {

    @GET("/repositories")
    suspend fun getAllRepositories(): Response<List<RepositoriesModel>>
}