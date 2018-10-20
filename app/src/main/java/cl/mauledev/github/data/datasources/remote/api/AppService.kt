package cl.mauledev.github.data.datasources.remote.api

import cl.mauledev.github.data.model.PullRequest
import cl.mauledev.github.data.model.Response
import cl.mauledev.github.utils.Constants
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppService {

    @GET("/search/repositories")
    fun getRepos(@Query("q") search: String = Constants.DEFAULT_QUERY,
                 @Query("sort") sort: String = Constants.DEFAULT_SORT,
                 @Query("page") page: Int = 1): Flowable<Response>

    @GET("/repos/{user}/{repo_title}/pulls")
    fun getPullRequests(@Path("user") user: String = Constants.DEFAULT_QUERY,
                        @Path("repo_title") repoTitle: String = Constants.DEFAULT_SORT)
            : Flowable<List<PullRequest>>
}