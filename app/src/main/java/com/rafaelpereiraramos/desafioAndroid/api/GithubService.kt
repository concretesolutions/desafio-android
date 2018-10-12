package com.rafaelpereiraramos.desafioAndroid.api

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Rafael P. Ramos on 12/10/2018.
 */
interface GithubService {

    @GET("search/repositories")
    fun search(@Query("q") query: String)
}