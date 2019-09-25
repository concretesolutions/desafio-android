package br.edu.ifsp.scl.desafio_android.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesService {

    @GET("/search/repositories?")
    fun searchRepositories(
        @Query("q") q: String,      // language:Java
        @Query("sort") sort: String,// stars
        @Query("page") page: Int)   // 1
            : Call<ResponseBody>
}