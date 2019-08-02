package com.example.challengecskotlin.api

import com.example.challengecskotlin.models.SearchResponse
import com.example.challengecskotlin.api.GithubApi.PARAM_PAGE
import com.example.challengecskotlin.api.GithubApi.PARAM_QUERY
import com.example.challengecskotlin.api.GithubApi.PARAM_SORT
import com.example.challengecskotlin.models.PullRequestObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //função de requisição a busca de repositórios
    @GET("search/repositories")
    fun fetchRepositories(@Query(PARAM_QUERY) query: String,
                      @Query(PARAM_SORT) sort: String,
                      @Query(PARAM_PAGE) page: String) : Call<SearchResponse>

    //função de requisição aos pull requests dos repositórios
    @GET("/repos/{login}/{name}/pulls")
    fun fetchPullRequests(@Path("login") login: String,
                          @Path("name") name: String) : Call<List<PullRequestObject>>
}