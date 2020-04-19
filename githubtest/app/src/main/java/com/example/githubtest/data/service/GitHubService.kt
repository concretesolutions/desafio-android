package com.example.githubtest.data.service

import com.example.githubtest.data.model.RepositoryResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {
    @GET("search/repositories")
    fun getRepositories(@Query("q") language: String,
                        @Query("sort") sort: String,
                        @Query("page") pagina: Int): Observable<RepositoryResponse>
}