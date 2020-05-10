package com.hotmail.fignunes.desafioconcretesolution.repository.remote.pullresquest.services

import com.hotmail.fignunes.desafioconcretesolution.repository.remote.pullresquest.responses.PullRequestResponses
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestServices {

    @GET("repos/{login}/{repository}/pulls")
    fun getPullRequest(
        @Path("login") login: String,
        @Path("repository") repository: String
    ): Single<Response<List<PullRequestResponses>>>
}