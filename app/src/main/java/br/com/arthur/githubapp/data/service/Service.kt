package br.com.arthur.githubapp.data.service

import br.com.arthur.githubapp.model.GitRepositories
import br.com.arthur.githubapp.model.PullRequest
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("search/repositories")
    fun getRepositoriesAsync(
        @Query("q") language: String = "Java",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int,
        @Query("per_page") limit: Int = 30
    ): Deferred<Response<GitRepositories>>

    @GET("repos/{creator}/{repository}/pulls")
    fun getPullRequestsRepositoryAsync(
        @Path("creator") creator: String,
        @Path("repository") repository: String
    ): Deferred<Response<List<PullRequest>>>

}