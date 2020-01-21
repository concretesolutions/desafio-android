package br.com.rmso.popularrepositories.retrofit.services

import br.com.rmso.popularrepositories.model.RepositoryListCallback
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {
    @GET("/search/repositories?q=language:Java&sort=stars")
    fun listRepositories(@Query("page") page: Int): Call<RepositoryListCallback>
}