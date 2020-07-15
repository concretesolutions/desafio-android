package com.bassul.mel.app.endpoint

import com.bassul.mel.app.feature.pullRequestsList.repository.model.PullRequestListResponse
import com.bassul.mel.app.feature.repositoriesList.repository.model.RepositoriesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    companion object {
        val BASE_URL = "https://api.github.com/"
    }

    @GET("search/repositories?q=language:java&sort=stargazers_count")
    fun fetchRepository(@Query("page") id: Int): Call<RepositoriesListResponse>

    @GET("repos/{login}/{repositoryName}/pulls")
    fun fetchPullRequest(
        @Path("login") login: String,
        @Path("repositoryName") repositoryName: String
    ): Call<List<PullRequestListResponse>>
}