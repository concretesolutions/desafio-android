package com.example.lucasdias.mvvmpoc.data.service

import com.example.lucasdias.mvvmpoc.domain.entity.RepositoryList
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface RepositoryService {

    @GET("/search/repositories?q=language:Java&sort=stars")
    fun loadRepositoryPageFromApi(@Query("page") page: Int) : Deferred<Response<RepositoryList>>
}