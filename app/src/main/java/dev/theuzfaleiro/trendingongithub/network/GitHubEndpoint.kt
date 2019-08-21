package dev.theuzfaleiro.trendingongithub.network

import dev.theuzfaleiro.trendingongithub.ui.feature.home.model.response.Repositories
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.model.response.PullRequest
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubEndpoint {
    @GET("search/repositories")
    fun fetchRepositoriesFromApi(
        @Query("page") page: Int,
        @Query("q") language: String = "language:Java"
    ): Single<Repositories>

    @GET("repos/{username}/{repositoryName}/pulls")
    fun fetchPullRequestsFromApi(
        @Path("username") username: String,
        @Path("repositoryName") repositoryName: String
    ): Observable<List<PullRequest>>
}