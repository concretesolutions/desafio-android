package com.example.gitrepositories.model.services

import com.example.gitrepositories.model.dto.PullRequestResponse
import com.example.gitrepositories.model.dto.RepositoryResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class GitHubService {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val baseService = retrofit.create(GitHubService::class.java)

//    @GET("/everything?q=sports&apiKey=aa67d8d98c8e4ad1b4f16dbd5f3be348")
//    fun getRepositories(@Query("page") page: Int, @Query("pageSize") pageSize: Int): Single<RepositoryResponse> {
//        return
//    }
//
//    @GET("/everything?q=sports&apiKey=aa67d8d98c8e4ad1b4f16dbd5f3be348")
//    fun getPullRequests(@Query("page") page: Int, @Query("pageSize") pageSize: Int): Single<PullRequestResponse> {
//        return
//    }
}