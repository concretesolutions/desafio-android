package com.rafael.fernandes.data.network

import com.rafael.fernandes.domain.model.GitPullRequests
import com.rafael.fernandes.domain.model.GitRepositories
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {
    @GET("search/repositories?q=language:Java")
    fun getOnlineRepositories(
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): Single<GitRepositories>

    @GET("repos/{user}/{repository}/pulls?state=all")
    fun listPullRequests(
        @Path("user") user: String,
        @Path("repository") repository: String
    ): Single<List<GitPullRequests>>
}