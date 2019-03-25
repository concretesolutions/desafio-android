package dev.renatoneto.githubrepos.network.github

import dev.renatoneto.githubrepos.model.github.GithubPullRequest
import dev.renatoneto.githubrepos.network.github.data.GithubRepositoriesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    fun getRepositoriesList(@Query("q") query: String,
                            @Query("sort") sort: String,
                            @Query("page") page: Int): Observable<GithubRepositoriesResponse>

    @GET("repos/{owner}/{repository}/pulls")
    fun getPullRequestsList(@Path("owner") owner: String,
                            @Path("repository") repository: String):
            Observable<ArrayList<GithubPullRequest>>

}