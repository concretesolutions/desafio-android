package com.example.desafio_android.data.repositories.source

import com.example.desafio_android.data.model.repositoriesInfo
import com.example.desafio_android.data.pullmodel.RepositoryPulls
import com.example.desafio_android.data.pullmodel.RepositoryPullsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

//private const val LANG = "language:Java"

interface RemoteApiSource {

    @GET("search/repositories?q=language:Java")
    fun getApiRepositories(): Call<repositoriesInfo>

    @GET("repos/{ownerName}/{repoName}/pulls")
    fun getRepositoriesPull(
        @Path ("ownerName") ownerName : String,
        @Path("repoName") repoName : String
    ): Call<List<RepositoryPullsItem>>

}

