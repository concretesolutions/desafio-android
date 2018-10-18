package matheusuehara.github.api

import matheusuehara.github.model.PullRequest
import matheusuehara.github.model.RepositoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    @GET("search/repositories")
    fun getRepositories(@Query("q") language: String,
                        @Query("sort") sort: String,
                        @Query("page") pagina: Int): Call<RepositoryResponse>

    @GET("repos/{owner}/{repository}/pulls")
    fun getPullRequests(@Path("owner") owner: String,
                        @Path("repository") repository: String,
                        @Query("state") state: String): Call<List<PullRequest>>

}
