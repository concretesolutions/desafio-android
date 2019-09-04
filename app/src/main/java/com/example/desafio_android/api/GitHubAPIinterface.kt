package com.example.desafio_android.api

import com.example.desafio_android.pojo.pullrequests.PullRequest
import com.example.desafio_android.pojo.repositories.Repositories
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubAPIinterface {
    @GET("search/repositories")
    fun getRepositories(@Query("page") page: Int, @Query("q") language: String = "language:java", @Query("sort") sort: String = "stars"): Call<Repositories>

    @GET("repos/{criador}/{repositorio}/pulls")
    fun getPullRequests(@Path("criador") criador: String, @Path("repositorio") repositorio: String): Call<List<PullRequest>>

}