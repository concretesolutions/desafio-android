package com.jsouza.repocatalog.data.repocatalog.remote

import com.jsouza.repocatalog.data.repocatalog.remote.response.RepositoryList
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryCatalogService {

    @GET("/search/repositories?q=language:Java&sort=stars")
    fun loadRepositoryPageFromApi(@Query("page") page: Int): Deferred<RepositoryList>
}
