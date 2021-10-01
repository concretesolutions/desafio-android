package com.concrete.challenge.domain.io

import com.concrete.challenge.domain.io.response.RepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    suspend fun getRepositories(
        @Query("page") page: Int
    ): RepositoriesResponse

}