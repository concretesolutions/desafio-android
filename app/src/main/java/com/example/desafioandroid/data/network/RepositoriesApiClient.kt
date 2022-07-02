package com.example.desafioandroid.data.network

import com.example.desafioandroid.data.model.PullModel
import com.example.desafioandroid.data.model.SearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepositoriesApiClient {

    @GET("/search/repositories")
    suspend fun getAllRepositories(
        @Query("q") search: String,
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 30
    ): Response<SearchModel>

    @GET("/repos/{owner}/{repo}/pulls")
    suspend fun getPullByOwner(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<List<PullModel>>
}