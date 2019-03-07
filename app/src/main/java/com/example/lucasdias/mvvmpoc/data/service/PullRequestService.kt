package com.example.lucasdias.mvvmpoc.data.service

import com.example.lucasdias.mvvmpoc.domain.entity.PullRequest
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PullRequestService {

    @Headers("Accept:application/vnd.github.symmetra-preview+json")

    @GET("/repos/{fullname}/pulls")
    fun loadPullRequestPageFromApi(@Path("fullname", encoded = true) fullname: String) : Deferred<Response<ArrayList<PullRequest>>>

}