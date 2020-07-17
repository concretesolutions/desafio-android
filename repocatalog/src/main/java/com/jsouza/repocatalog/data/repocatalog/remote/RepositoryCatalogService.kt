package com.jsouza.repocatalog.data.repocatalog.remote

import com.jsouza.repocatalog.data.repocatalog.remote.response.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryCatalogService {

    @GET("/search/repositories?sort=stars")
    suspend fun loadRepositoryPageFromApiAsync(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): RepositoryResponse
}
