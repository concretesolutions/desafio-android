package com.silvioapps.githubkotlin.features.list.services

import com.silvioapps.githubkotlin.features.list.models.ListModel
import com.silvioapps.githubkotlin.constants.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ListService {
    @Headers("")
    @GET(Constants.LIST)
    fun getList(@Query("query") search : String, @Query("page") page : Int) : Call<MutableList<ListModel>>
}