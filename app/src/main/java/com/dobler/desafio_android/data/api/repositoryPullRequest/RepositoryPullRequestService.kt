package com.dobler.desafio_android.data.api.repositoryPullRequest

import com.dobler.desafio_android.data.model.RepositoryPullRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface RepositoryPullRequestService {

    @GET("repos/{user}/{repository}/pulls")
    fun getList(
        @Path("user") user: String,
        @Path("repository") repository: String
    ):
            Call<List<RepositoryPullRequest>>

}
