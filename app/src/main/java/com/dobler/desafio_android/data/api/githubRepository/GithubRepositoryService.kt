package com.dobler.desafio_android.data.api.githubRepository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubRepositoryService {

    @GET("search/repositories")
    fun getPage(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): Call<GithubRepositoryResponse>

}
