package com.ruiderson.desafio_android.api

import com.ruiderson.desafio_android.models.PullRequest
import com.ruiderson.desafio_android.models.RepositoryBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Call

interface ApiGithub {


    @GET("search/repositories")
    fun getRepos(@Query("q") language: String, @Query("sort") sort: String, @Query("page") page: Int) : Call<RepositoryBody>


    @GET("repos/{username}/{repository}/pulls")
    fun getPulls(@Path("username") user: String, @Path("repository") repository: String) : Call<ArrayList<PullRequest>>


}