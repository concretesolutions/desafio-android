package com.example.challengecskotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubApi {
    private const val GITHUB_BASE_URL = "https://api.github.com/"
    const val PARAM_QUERY = "q"
    const val PARAM_SORT = "sort"
    const val SORT_BY_STARS = "stars"
    const val PARAM_PAGE = "page"
    lateinit var searchService: ApiService
    init {
        buildRetrofit()
    }

    private fun buildRetrofit() {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(GITHUB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        searchService = retrofit.create(ApiService::class.java)
    }
}