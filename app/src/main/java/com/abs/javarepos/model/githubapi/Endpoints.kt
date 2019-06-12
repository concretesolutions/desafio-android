package com.abs.javarepos.model.githubapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoints {
    @GET("search/repositories?q=language:Java&sort=stars")
    fun getRepos(
        @Query("page") page: Int
    ): Call<RepoResponse>
}