package cl.getapps.githubjavarepos.feature.repos.data.remote

import cl.getapps.githubjavarepos.feature.repos.data.ReposResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


interface ReposAPI {
    companion object {
        private const val REPOS = "search/repositories"
    }

    @GET(REPOS)
    fun fetchRepos(
        @Query("page") page: String = "1",
        @Query("q") query: String = "language:Java",
        @Query("sort") sort: String = "stars"
    ): Flowable<ReposResponse>
}