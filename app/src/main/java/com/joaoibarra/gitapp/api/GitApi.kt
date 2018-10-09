package com.joaoibarra.gitapp.api

import com.joaoibarra.gitapp.api.model.Pull
import com.joaoibarra.gitapp.api.model.Result
import io.reactivex.Observable
import retrofit2.http.*

public interface GitApi {
    @Headers(
            "Accept: application/vnd.github.v3.text-match+json",
            "Content-type:application/json"
    )
    @GET("search/repositories")
    fun searchByQuery(@Query("q") q: String = "language:Java",
                      @Query("sort") sort: String = "stars",
                      @Query("page") page: Int) : Observable<Result>

    @Headers(
            "Accept: application/vnd.github.v3.text-match+json",
            "Content-type:application/json"
    )
    @GET("repos/{owner}/{repo}/pulls")
    fun searchPullByRepository(@Path("owner") owner: String,
                               @Path("repo") repo: String,
                               @Query("page") page: Int) : Observable<List<Pull>>
}