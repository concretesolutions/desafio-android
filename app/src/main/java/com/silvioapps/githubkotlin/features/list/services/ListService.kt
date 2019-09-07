package com.silvioapps.githubkotlin.features.list.services

import com.silvioapps.githubkotlin.features.list.models.ResponseModel
import com.silvioapps.githubkotlin.constants.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ListService {
    @GET(Constants.LIST)
    fun getList(@Query("q") q : String, @Query("sort") sort : String, @Query("page") page : Int) : Call<ResponseModel>
}