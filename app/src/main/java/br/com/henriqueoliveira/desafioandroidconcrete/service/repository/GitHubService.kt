package br.com.henriqueoliveira.desafioandroidconcrete.service.repository

import br.com.henriqueoliveira.desafioandroidconcrete.service.models.PullRequest
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.Repository
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.ServerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

    @GET("search/repositories")
    fun getRepositoryList(@Query("q") technology: String, @Query("page") page: Int, @Query("sort") sort: String): Call<ServerResponse>

    @GET("repos/{owner}/{repository}/pulls")
    fun getPullRequests(@Path("owner") owner: String, @Path("repository") repository: String): Call<List<PullRequest>>



}