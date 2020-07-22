package com.jsouza.repopullrequests.data.remote

import com.jsouza.repopullrequests.data.remote.response.PullsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestsService {

    @GET("/repos/{username}/{repoName}/pulls")
    suspend fun loadPullsPageFromApiAsync(
        @Path("username") username: String,
        @Path("repoName") repoName: String
    ): List<PullsResponse>
}
