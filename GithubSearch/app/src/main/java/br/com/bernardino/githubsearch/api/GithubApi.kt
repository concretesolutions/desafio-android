package br.com.bernardino.githubsearch.api

import br.com.bernardino.githubsearch.model.PullRequest
import br.com.bernardino.githubsearch.model.Repository
import br.com.bernardino.githubsearch.model.RepositoryBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    fun getRepositories(@Query("q") language: String, @Query("sort") sort: String, @Query("page") page: Int) : Call<RepositoryBody>

    @GET ("repos/{creator}/{repository}/pulls")
    fun getPullRequests (@Path("creator") creator: String?,
                         @Path("repository") repository : String?) : Call<List<PullRequest>>
}