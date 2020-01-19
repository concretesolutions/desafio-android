package com.concretesolutions.desafioandroid.service

import com.concretesolutions.desafioandroid.model.Repositories
import com.concretesolutions.desafioandroid.model.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepositoryService {

    @GET("search/repositories?q=language:Java&sort=stars")
    fun getRepositories(@Query("page") searchTerm: String, @Query("page") sortType: String, @Query("page") page: Number): Call<Repositories>

    @GET("repos/{owner}/{repository}/pulls")
    fun getRepository(@Path("owner") owner: Number, @Path("repository") repository: Number): Call<Repository>

}