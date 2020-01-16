package com.concretesolutions.desafioandroid.helpers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkHelper {

    companion object {
        private const val retrofitBasePath = "https://api.github.com/"

        fun getRetrofitInstance(path: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getRetrofitInstanceGitHub() : Retrofit {
            return getRetrofitInstance(retrofitBasePath)
        }
    }

}