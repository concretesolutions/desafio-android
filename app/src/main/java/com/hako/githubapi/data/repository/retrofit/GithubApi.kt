package com.hako.githubapi.data.repository.retrofit

import com.hako.githubapi.domain.entities.PullRequest
import com.hako.githubapi.domain.entities.Repositories
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("/search/repositories")
    fun getTopRepositories(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): Observable<Repositories>

    @GET("/repos/{author}/{name}/pulls")
    fun getPullRequests(
        @Path("author") author: String,
        @Path("name") name: String
    ): Observable<List<PullRequest>>
}
