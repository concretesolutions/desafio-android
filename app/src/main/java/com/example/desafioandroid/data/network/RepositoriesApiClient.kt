package com.example.desafioandroid.data.network

import com.example.desafioandroid.data.model.PullModel
import com.example.desafioandroid.data.model.RepositoriesModel
import com.example.desafioandroid.data.model.RepoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RepositoriesApiClient {

    @GET("/repositories")
    suspend fun getAllRepositories(): Response<List<RepositoriesModel>>

    @GET("/repos/{owner}/{repo}/pulls")
    suspend fun getPullByOwner(@Path("owner") owner : String, @Path("repo") repo : String): Response<List<PullModel>>

    @GET("/repos/{owner}/{repo}")
    suspend fun getRepoByOwner(@Path("owner") owner : String, @Path("repo") repo : String): Response<RepoModel>

}