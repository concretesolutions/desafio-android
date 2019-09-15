package com.desafioandroid.data.source.remote

import com.desafioandroid.data.model.home.entity.HomeResponse
import com.desafioandroid.data.model.pullrequest.entity.PullRequestResponse

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun getListHome(@Query("page") page: Int): HomeResponse

    @GET("repos/{user}/{repository}/pulls")
    suspend fun getListPullRequest(@Path("user") user: String, @Path("repository") repository: String): List<PullRequestResponse>
}
