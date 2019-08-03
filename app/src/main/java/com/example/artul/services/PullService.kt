package com.example.artul.services

import com.example.artul.models.PullRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PullService {

    @GET("repos/{owner}/{repository}/pulls")

    fun list(@Path("owner") owner: String, @Path("repository") repository: String): Call<List<PullRequest>>

}