package com.rafaelpereiraramos.desafioAndroid.api

import com.rafaelpereiraramos.desafioAndroid.database.model.PullTO
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

    /*@GET("repos/{owner}/{repo}/pulls")
    fun getPull(@Path("owner") login: String,
                @Path("repo") repo:String,
                @Query("page") page:Int,
                @Query("per_page") itemsPerPage: Int): Call<PullGetResponse>*/

    @GET("repos/{owner}/{repo}/pulls")
    fun getPull(@Path("owner") ownerLogin: String,
                @Path("repo") repo:String,
                @Query("page") page:Int,
                @Query("per_page") itemsPerPage: Int): Call<List<PullTO>>
}