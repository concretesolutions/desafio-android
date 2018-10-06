package com.concrete.andresdavid.desafioandroid.remote

import com.concrete.andresdavid.desafioandroid.model.RepositoryResponse
import com.concrete.andresdavid.desafioandroid.model.PullRequest
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {
    @GET("search/repositories")
    fun javaRepositories(@Query("q") q: String = "language:Java",
                         @Query("sort") sort: String = "stars",
                         @Query("page") page: Int = 0,
                         @Query("per_page") pageSize: Int = 10): Observable<RepositoryResponse>

    @GET("repos/{owner}/{repo}/pulls")
    fun repoPullRequest(@Path("owner") owner: String,
                        @Path("repo") repoName: String): Observable<List<PullRequest>>

    companion object Factory {
        val BASE_URL = "https://api.github.com/"
        fun create(): GithubApiService {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(GithubApiService::class.java)
        }
    }
}