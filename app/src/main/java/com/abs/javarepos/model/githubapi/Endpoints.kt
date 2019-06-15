package com.abs.javarepos.model.githubapi

import com.abs.javarepos.model.PullRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoints {
    @GET("search/repositories?q=language:Java&sort=stars")
    fun getRepos(
        @Query("page") page: Int
    ): Call<RepoResponse>

    @GET("repos/{owner}/{repo}/pulls")
    fun getPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<List<PullRequest>>
}