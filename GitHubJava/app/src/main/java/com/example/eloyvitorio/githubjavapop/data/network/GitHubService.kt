package com.example.eloyvitorio.githubjavapop.data.network

import com.example.eloyvitorio.githubjavapop.data.model.PullRequest
import com.example.eloyvitorio.githubjavapop.data.model.ResponseResult

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {
    @GET("search/repositories?q=language:Java")
    fun javaRepositories(@Query("sort") sort: String = "stars",
                         @Query("page") page: Int): Call<ResponseResult>

    @GET("repos/{ownerLogin}/{repositoryName}/pulls")
    fun getRepositoryPullRequests(@Path("ownerLogin") ownerLogin: String,
                                  @Path("repositoryName") repositoryName: String): Call<List<PullRequest>>
}
