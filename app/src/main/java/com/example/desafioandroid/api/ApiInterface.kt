package com.example.desafioandroid.api

import com.example.desafioandroid.schema.PullItem
import com.example.desafioandroid.schema.SearchRepository
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    //Trae toda la lista de repositorios
    @GET("/search/repositories")
    fun getRepositories(@Query("q") language: String,
                         @Query("sort") sort: String,
                         @Query("page") page: Int)
                        : Deferred<Response<SearchRepository>>

    //Trae todos los pull request asociados a ese repositorio
    @GET("repos/{creator}/{name_repository}/pulls")
    fun getPull(@Path("creator") creator: String,
                @Path("name_repository") name_repository: String): Deferred<Response<List<PullItem>>>



}