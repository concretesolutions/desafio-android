package cl.getapps.githubjavarepos.feature.repos.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


internal interface ReposAPI {
    companion object {
        private const val REPOS = "{search}/{repositories}"
    }

    @GET(REPOS)
    fun repos(
        @Path("search") search: String = "search",
        @Path("repositories") repositories: String = "repositories",
        @Query("page") page: String = "1",
        @Query("q") query: String = "language:Java",
        @Query("sort") sort: String = "stars"
    ): Call<ReposResponse>
}