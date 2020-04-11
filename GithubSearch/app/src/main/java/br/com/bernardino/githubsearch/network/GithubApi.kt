package br.com.bernardino.githubsearch.network

import androidx.lifecycle.LiveData
import br.com.bernardino.githubsearch.model.PullRequest
import br.com.bernardino.githubsearch.model.RepositoryBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories?q=language:Java&sort=stars&per_page=10")
    suspend fun getRepositories(@Query("page") page: Int) : RepositoryBody
    @GET ("repos/{creator}/{repository}/pulls")
    suspend fun getPullRequests (@Path("creator") creator: String?,
                         @Path("repository") repository : String?) : List<PullRequest>
}