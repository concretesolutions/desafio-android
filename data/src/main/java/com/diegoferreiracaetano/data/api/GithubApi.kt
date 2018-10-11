package com.diegoferreiracaetano.data.api

import com.diegoferreiracaetano.data.items.Items
import com.diegoferreiracaetano.domain.Constants
import com.diegoferreiracaetano.domain.pull.Pull
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi{

    @GET("search/repositories")
    fun getListRepo(@Query("q") q:String = "language:Java",
                    @Query("sort") stars:String = "stars",
                    @Query("page") page:Int,
                    @Query("per_page") per_page :Int = Constants.PAGE_SIZE): Flowable<Items>

    @GET("repos/{owner}/{repo}/pulls")
    fun getPull( @Path("owner") onwer: String,
                  @Path("repo") repo:String,
                 @Query("page") page:Int,
                 @Query("per_page") per_page :Int = Constants.PAGE_SIZE): Flowable<List<Pull>>
}
