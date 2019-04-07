package com.example.desafioandroid.api

import com.example.desafioandroid.schemas.PullItem
import com.example.desafioandroid.schemas.SearchRepository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    //Trae toda la lista de repositorios
    @GET("/search/repositories?q=language:Java&sort=stars&page=1")
    fun getRepositories(@Query("q") language: String,
                         @Query("sort") sort: String,
                         @Query("page") page: Int)
                        : Call<SearchRepository>

    //trae todos los pull request asociados a ese repositorio
    @GET("/repos/{creator}/{name_repositores}/pulls")
    fun getAllCardIssuers(@Path("creator") creator: String,
                          @Query("name_repository") name_repository: String): Call<List<PullItem>>



}