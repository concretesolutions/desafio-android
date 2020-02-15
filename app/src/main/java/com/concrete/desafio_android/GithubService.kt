package com.concrete.desafio_android

import com.concrete.desafio_android.domain.PullRequest
import com.concrete.desafio_android.domain.Repository
import com.concrete.desafio_android.domain.RepositoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("search/repositories")
    fun listJavaRepositories(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") pageNumber: Int
    ): Call<RepositoryResponse?>?

//    @GET("repos/{creator}/{repository}/pulls")
//    fun listPullRequests(
//        @Path("creator") creator: String?,
//        @Path("repository") repository: String?
//    ): Call<List<PullRequest?>?>?

}