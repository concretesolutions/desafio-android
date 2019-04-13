package com.dobler.desafio_android.data.api.repositoryPullRequest

import com.dobler.desafio_android.data.model.RepositoryPullRequest
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface RepositoryPullRequestService {

    @GET("repos/{user}/{repositoryName}/pulls")
    fun getList(
        @Path("user") user: String,
        @Path("repositoryName") repositoryName: String
    ):
            Observable<List<RepositoryPullRequest>>

}
