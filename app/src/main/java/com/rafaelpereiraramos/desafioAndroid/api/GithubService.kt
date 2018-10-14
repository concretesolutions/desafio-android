package com.rafaelpereiraramos.desafioAndroid.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Rafael P. Ramos on 12/10/2018.
 */
interface GithubService {

    @GET("search/repositories?sort=stars")
    fun searchRepos(@Query("q") query: String,
                    @Query("page") page: Int,
                    @Query("per_page") itemsPerPage: Int): Call<RepoSearchResponse>

    @GET("repos/{owner}/{repo}/pulls")
    fun getPull(@Path("owner") owner: String,
                @Path("repo") repo:String,
                @Query("page") page:Int,
                @Query("per_page") itemsPerPage: Int): Call<PullGetResponse>
}