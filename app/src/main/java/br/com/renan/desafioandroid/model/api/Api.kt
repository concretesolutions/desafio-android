package br.com.renan.desafioandroid.model.api

import br.com.renan.desafioandroid.model.data.PullRequestList
import br.com.renan.desafioandroid.model.data.RepositoryItensList
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/repositories?")
    fun getRepositories(@Query("q") language: String,
                        @Query("sort") stars: String,
                        @Query("page") page: Int): Flowable<RepositoryItensList>

    @GET("repos/{login}/{name}/pulls")
    fun getPullRequests(@Path(value = "login") login: String,
                        @Path(value = "name") name: String): Flowable<PullRequestList>
}