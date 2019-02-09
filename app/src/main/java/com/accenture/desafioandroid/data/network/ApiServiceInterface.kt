package com.accenture.desafioandroid.data.network

import com.accenture.desafioandroid.mvvm.model.PullRequest
import com.accenture.desafioandroid.mvvm.model.RepositoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {
    @Headers("Accept: application/vnd.github.v3+json")
    @GET("search/repositories")
    fun getRepository(
        @Query("q") searchQuery: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): Call<RepositoryResponse>

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("repos/{criador}/{repositorio}/pulls")
    fun getPullRequest(@Path("criador") owner: String, @Path("repositorio") repo: String): Call<MutableList<PullRequest>>

}
