package com.example.gitrepositories.model.services

import com.example.gitrepositories.model.dto.PullRequest
import com.example.gitrepositories.model.dto.Repository
import com.example.gitrepositories.model.dto.RepositoryResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

    @GET("search/repositories?q=language:Java&sort=stars")
    fun getRepositories(@Query("page") page: Int): Call<RepositoryResponse>

    @GET("repos/{creator}/{name}/pulls")
    fun getPullRequests(@Path("creator") repoCreator: String, @Path("name") repoName: String): Call<List<PullRequest>>

    companion object {
        fun getBaseService(): GitHubService {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(GitHubService::class.java)
        }
    }
}