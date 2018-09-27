package br.com.desafio.concrete.network

import br.com.desafio.concrete.model.GitHubResponse
import br.com.desafio.concrete.model.PullRequest
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Malkes on 24/09/2018.
 */
interface WSInterface {

    @GET("search/repositories")
    fun fetchRepositories(@Query("q") technology: String, @Query("page") page: Int, @Query("sort") sort: String): Observable<GitHubResponse>

    @GET("repos/{owner}/{repository}/pulls")
    fun fetchPullRequests(@Path("owner") owner: String, @Path("repository") repository: String): Observable<ArrayList<PullRequest>>
}