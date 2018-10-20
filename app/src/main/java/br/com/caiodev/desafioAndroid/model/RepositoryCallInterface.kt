package br.com.caiodev.desafioAndroid.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepositoryCallInterface {

    @GET("search/repositories")
    fun getRepoList(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("per_page") maxResults: Int
    ): Single<RepositoryListModel>

    @GET("users/{ownerLogin}")
    fun getOwnerData(@Path("ownerLogin") ownerLogin: String): Single<RepositoryOwnerModel>
}