package com.example.challengecskotlin

import com.example.challengecskotlin.GithubApi.PARAM_PAGE
import com.example.challengecskotlin.GithubApi.PARAM_QUERY
import com.example.challengecskotlin.GithubApi.PARAM_SORT
import com.example.challengecskotlin.GithubApi.SORT_BY_STARS
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/repositories")
    fun fetchAllUsers(@Query(PARAM_QUERY) query: String,
                      @Query(PARAM_SORT) sort: String,
                      @Query(PARAM_PAGE) page: String) : Call<SearchResponse>

    @GET("/repos/{login}/{name}/pulls")
    fun fetchPullRequests(@Path("login") login: String,
                          @Path("name") name: String) : Call<List<PullRequestObject>>
}