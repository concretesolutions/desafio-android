package cl.carteaga.querygithub.apis

import cl.carteaga.querygithub.models.PullRequest
import cl.carteaga.querygithub.models.Repository
import retrofit2.Call
import retrofit2.http.*


interface GitHubEndPoints {
    @GET("search/repositories")
    fun getRepositories(
        @Query("q") q: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ) : Call<Repository>

    @GET("repos/{user}/{repository}/pulls")
    fun getPullRequestRepository(
        @Path("user") user: String,
        @Path("repository") repository: String
    ) : Call<List<PullRequest>>
}