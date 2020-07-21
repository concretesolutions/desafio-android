package com.jsouza.repocatalog.data.remote

import com.jsouza.repocatalog.data.remote.response.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoCatalogService {

    @GET("/search/repositories?q=language:Java&?sort=stars")
    suspend fun loadRepositoryPageFromApiAsync(
        @Query("page") page: Int
    ): RepositoryResponse
}
