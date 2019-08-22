package com.joaodomingos.desafio_android.api

import com.joaodomingos.desafio_android.models.PullRequestModel
import com.joaodomingos.desafio_android.models.SearchListItensModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {
    @GET("search/repositories?q=language:Java&sort=stars")
    fun getSearchListItens(@Query("page") page: Int): Single<SearchListItensModel>

    @GET("repos/{user}/{repository}/pulls")
    fun getPullRequestListItens(@Path( "user") user: String, @Path("repository") repository: String): Single<List<PullRequestModel>>

    companion object {
        fun getService(): NetworkService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(NetworkService::class.java)
        }
    }
}