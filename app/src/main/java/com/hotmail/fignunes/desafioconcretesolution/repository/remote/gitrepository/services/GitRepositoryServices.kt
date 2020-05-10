package com.hotmail.fignunes.desafioconcretesolution.repository.remote.gitrepository.services

import com.hotmail.fignunes.desafioconcretesolution.repository.remote.gitrepository.responses.GitRepositoryResponses
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitRepositoryServices {

    @GET("search/repositories")
    fun getGitRepositories(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): Single<Response<GitRepositoryResponses>>
}