package br.com.rmso.popularrepositories.retrofit.services

import br.com.rmso.popularrepositories.model.PullRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestService {
    @GET("/repos/{owner}/{repository}/pulls")
    fun listPullRequests(@Path("owner") owner: String, @Path("repository") repository: String): Call<List<PullRequest>>
}