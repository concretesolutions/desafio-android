package com.concrete.desafio.data.interfaces

import com.concrete.desafio.data.Repositorio
import com.concrete.desafio.data.Repositorios
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

//    @GET("users/{user}/repos")
//    fun listRepos(@Path("user") user: String):
//            Deferred<Response<List<Repositorio>>>


    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    fun listarRepositorios(): Call<Repositorios>
}