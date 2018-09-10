package com.concrete.desafioandroid.data.source.remote.network

import com.concrete.desafioandroid.data.models.OwnerDetails
import com.concrete.desafioandroid.data.models.PullRequest
import com.concrete.desafioandroid.data.models.RequestRepo
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url


interface RestApi {

    @GET("search/repositories")
    fun getReposList(@QueryMap queries: HashMap<String, String>): Flowable<RequestRepo>

    @GET
    fun getOwnerDetails(@Url url: String): Flowable<OwnerDetails>

    @GET
    fun getPullsRequests(@Url url: String): Flowable<List<PullRequest>>

}