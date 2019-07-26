package com.example.challengecskotlin

import com.example.challengecskotlin.GithubApi.PARAM_PAGE
import com.example.challengecskotlin.GithubApi.PARAM_QUERY
import com.example.challengecskotlin.GithubApi.PARAM_SORT
import com.example.challengecskotlin.GithubApi.SORT_BY_STARS
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/repositories")
    fun fetchAllUsers(@Query("q") query: String) : Call<SearchResponse>
}