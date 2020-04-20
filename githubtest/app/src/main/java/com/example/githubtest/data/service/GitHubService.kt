package com.example.githubtest.data.service

import com.example.githubtest.data.model.PullRequest
import com.example.githubtest.data.model.RepositoryResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {
    @GET("search/repositories")
    fun getRepositories(@Query("q") language: String,
                        @Query("sort") sort: String,
                        @Query("page") pagina: Int): Observable<RepositoryResponse>

    @GET("repos/{owner}/{repository}/pulls")
    fun getPullRequests(@Path("owner") owner: String,
                        @Path("repository") repository: String,
                        @Query("state") state: String): Observable<ArrayList<PullRequest>>
}