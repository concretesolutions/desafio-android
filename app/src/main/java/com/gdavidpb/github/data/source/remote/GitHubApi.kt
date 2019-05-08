package com.gdavidpb.github.data.source.remote

import com.gdavidpb.github.data.model.api.PullEntry
import com.gdavidpb.github.data.model.api.RepositoryEntry
import com.gdavidpb.github.data.model.api.SearchResultEntry
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/repositories")
    fun getRepositories(
        @Query("q") query: String = "language:Java",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int
    ): Call<SearchResultEntry<RepositoryEntry>>

    @GET("repos/{repository}/pulls")
    fun getPulls(
        @Path("repository", encoded = true) repository: String
    ): Call<List<PullEntry>>
}