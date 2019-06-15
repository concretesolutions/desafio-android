package com.concretesolutions.diegosouza.concretesolutions.Service

import com.concretesolutions.diegosouza.concretesolutions.Model.Lista
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ListaService {

    @GET("repositories?q=language:Java&sort=stars&page=1")
    fun lista(@Query("offset") offset: Int) : Call<Lista>
}