package com.marraps.mvvmshow.retrofit


import com.rafaelmfer.consultinggithub.model.pullrequests.GitPullRequestResponse
import com.rafaelmfer.consultinggithub.model.repositories.GitRepositoriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    @GET("search/repositories?q=language:Java&sort=stars")
    fun getRepositoriesList(
        @Query("page") page: Int
    ): Call<GitRepositoriesResponse>

    @GET("repos/{creator}/{repository}/pulls")
    fun getPullRequests(
        @Path("creator") creatorString: String,
        @Path("repository") repoString: String,
        @Query("page") page: Int
    ): Call<GitPullRequestResponse>
}