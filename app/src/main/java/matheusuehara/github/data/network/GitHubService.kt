package matheusuehara.github.data.network

import io.reactivex.Observable
import matheusuehara.github.data.model.PullRequest
import matheusuehara.github.data.model.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

    @GET("search/repositories")
    fun getRepositories(@Query("q") language: String,
                        @Query("sort") sort: String,
                        @Query("page") pagina: Int): Observable<RepositoryResponse>

    @GET("repos/{owner}/{repository}/pulls")
    fun getPullRequests(@Path("owner") owner: String,
                        @Path("repository") repository: String,
                        @Query("state") state: String): Observable<ArrayList<PullRequest>>

}
