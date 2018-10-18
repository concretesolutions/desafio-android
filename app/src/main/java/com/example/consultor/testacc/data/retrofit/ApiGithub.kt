package com.example.consultor.testacc.data.retrofit

import com.example.consultor.testacc.data.pojos.PullModel
import com.example.consultor.testacc.data.pojos.RepoPage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiGithub {



    @GET("search/repositories?q=language:Java&sort=stars")
    fun getRepositoriesAt(@Query("page") page:Int): Call<RepoPage>


    @GET("repos/{criador}/{repositorio}/pulls")
    fun getRepositoryPulls(@Path("criador") criador: String, @Path("repositorio") repo: String):Call<MutableList<PullModel>>


    @GET("repos/{criador}/{repositorio}/issues")
    fun getRepositoryIssues(@Path("criador") criador: String, @Path("repositorio") repo: String):Call<MutableList<PullModel>>


}