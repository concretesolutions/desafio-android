package com.concretesolutions.diegosouza.concretesolutions.Api

import com.concretesolutions.diegosouza.concretesolutions.Service.ListaService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/search/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun listaService() = retrofit.create(ListaService::class.java)
}