package com.germanofilho.network.feature.repositorylist.data.source.remote.api

import com.germanofilho.network.feature.repositorylist.model.entity.GitRepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitRepositoryApi {
    @GET("search/repositories")
    suspend fun getRepositoryList(@Query("q") language: String = "language:Java",
                          @Query("sort") sort: String = "starts",
                          @Query("page") page: Int = 1) : GitRepositoryResponse
}