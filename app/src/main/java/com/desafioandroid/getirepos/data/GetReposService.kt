package com.desafioandroid.getirepos.data

import com.desafioandroid.getirepos.data.dto.PullsResponse
import com.desafioandroid.getirepos.data.dto.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetReposService {
    @GET("search/repositories?q=language:Java&sort=stars")
    fun getRepos(@Query("page") page: Int): Call<SearchResponse>

    @GET("repos/{owner}/{repository}/pulls")
    fun getPulls(@Path("owner") owner: String, @Path("repository") repository: String): Call<PullsResponse>
}