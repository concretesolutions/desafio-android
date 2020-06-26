package com.germanofilho.network.feature.pullrequestlist.data.source.remote.api

import com.germanofilho.network.feature.pullrequestlist.model.entity.GitPullRequestResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GitPullRequestApi {
    @GET("repos/{user}/{repo}/pulls")
    suspend fun getPullRequestList(@Path("user") user: String,
                           @Path("repo") repo: String) : List<GitPullRequestResponse>
}