package com.accenture.desafioandroid.data.network

import com.accenture.desafioandroid.utils.Constants.Companion.URL_SERVER
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

class Network {
    companion object {
        fun create(): ApiServiceInterface {

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(URL_SERVER)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}