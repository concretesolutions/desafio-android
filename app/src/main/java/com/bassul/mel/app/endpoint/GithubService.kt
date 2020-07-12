package com.bassul.mel.app.endpoint

import com.bassul.mel.app.repositoriesList.repository.model.RepositoriesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

        companion object {
            val BASE_URL = "https://api.github.com/search/"
        }

        @GET("repositories?q=language:java&sort=stargazers_count")
        fun fetchRepository(@Query("page") id: Int) : Call<RepositoriesListResponse>
}