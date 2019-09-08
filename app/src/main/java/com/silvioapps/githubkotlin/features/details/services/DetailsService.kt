package com.silvioapps.githubkotlin.features.details.services

import com.silvioapps.githubkotlin.features.details.models.DetailsModel
import com.silvioapps.githubkotlin.constants.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsService {
    @GET(Constants.DETAILS)
    fun getList(@Path(Constants.AUTHOR) author : String, @Path(Constants.REPO) repo : String) : Call<List<DetailsModel>>
}