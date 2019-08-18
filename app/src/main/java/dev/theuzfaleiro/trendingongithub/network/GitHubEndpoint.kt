package dev.theuzfaleiro.trendingongithub.network

import dev.theuzfaleiro.trendingongithub.ui.feature.home.model.response.Repositories
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubEndpoint {
    @GET("search/repositories")
    fun fetchRepositoriesFromApi(
        @Query("page") page: Int,
        @Query("q") language: String = "Kotlin"
    ): Single<Repositories>
}