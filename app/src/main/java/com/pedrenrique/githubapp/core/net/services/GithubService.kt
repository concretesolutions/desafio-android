package com.pedrenrique.githubapp.core.net.services

import com.pedrenrique.githubapp.core.data.PullRequest
import com.pedrenrique.githubapp.core.net.RepositoryResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface GithubService {
    @GET("search/repositories")
    fun list(@QueryMap params: Map<String, String>): Deferred<RepositoryResponse>

    @GET("repos/{user}/{repository}/pulls")
    fun listPR(
        @Path("user") user: String,
        @Path("repository") repository: String,
        @Query("page") page: Int
    ): Deferred<List<PullRequest>>
}