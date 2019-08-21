package com.concrete.desafio.data.interfaces

import com.concrete.desafio.data.PullRequest
import com.concrete.desafio.data.Repositorios
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

    @GET("search/repositories?q=language:Java&sort=stars")
    fun listarRepositorios(@Query("page") pagina: String): Call<Repositorios>

    @GET("repos/{autor}/{repositorio}/pulls")
    fun listarPullRequest(@Path("repositorio") repositorio: String, @Path("autor") autor: String): Call<List<PullRequest>>


}