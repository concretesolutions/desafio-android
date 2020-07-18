package com.jsouza.repodetail.data

import com.jsouza.repodetail.data.remote.response.PullsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepoDetailService {

    @GET("/repos/{username}/{repoName}/pulls")
    suspend fun loadPullsPageFromApiAsync(
        @Path("username") username: String,
        @Path("repoName") repoName: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): List<PullsResponse>
}
