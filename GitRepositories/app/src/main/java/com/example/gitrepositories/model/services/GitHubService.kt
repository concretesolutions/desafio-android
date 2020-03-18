package com.example.gitrepositories.model.services

import com.example.gitrepositories.model.dto.PullRequestResponse
import com.example.gitrepositories.model.dto.RepositoryResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {

    @GET("/everything?q=sports&apiKey=aa67d8d98c8e4ad1b4f16dbd5f3be348")
    fun getRepositories(@Query("page") page: Int, @Query("pageSize") pageSize: Int): Single<RepositoryResponse>

    @GET("/everything?q=sports&apiKey=aa67d8d98c8e4ad1b4f16dbd5f3be348")
    fun getPullRequests(@Query("page") page: Int, @Query("pageSize") pageSize: Int): Single<PullRequestResponse>

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