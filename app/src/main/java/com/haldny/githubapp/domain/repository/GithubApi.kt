package com.haldny.githubapp.domain.repository

import com.haldny.githubapp.domain.model.ResponsePullRequest
import com.haldny.githubapp.domain.model.ResponseRepository
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun getRepositories(@Query("page") page: Int): ResponseRepository

    @GET("repos/{owner}/{repository}/pulls")
    suspend fun getPullRequests(@Path("owner") owner: String,
                                @Path("repository") repository: String): List<ResponsePullRequest>

}