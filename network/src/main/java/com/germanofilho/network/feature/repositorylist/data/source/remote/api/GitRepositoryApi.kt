package com.germanofilho.network.feature.repositorylist.data.source.remote.api

import retrofit2.http.GET

interface GitRepositoryApi {

    @GET
    fun getRepositoryList()



}