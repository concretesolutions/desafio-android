package com.uharris.desafio_android.data.services

import com.uharris.desafio_android.domain.models.PullRequest
import com.uharris.desafio_android.domain.models.reponse.RepositoryResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepositoriesServices {

    @GET("/search/repositories?q=language:Java&sort=stars")
    fun getRepositories(@Query("page") page: Int): Deferred<Response<RepositoryResponse>>

    @GET("/repos/{creator}/{repository}/pulls")
    fun getRepositoryPullRequests(@Path("creator") creatorId: String,
                                  @Path("repository") repositoryId: String): Deferred<Response<List<PullRequest>>>
}