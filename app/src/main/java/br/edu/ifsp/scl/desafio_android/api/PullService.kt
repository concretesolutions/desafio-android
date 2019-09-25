package br.edu.ifsp.scl.desafio_android.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PullService {
    @GET("/repos/{login}/{repo}/pulls")
    fun getPullRequest(
        @Path("login") login: String,
        @Path("repo") repo: String)
            : Call<ResponseBody>
}