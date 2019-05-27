package com.losingtimeapps.data.remote

import com.losingtimeapps.data.model.PullRequest.PullRequestResponse
import com.losingtimeapps.data.model.RepositoryResponse
import com.losingtimeapps.data.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

    @GET("search/repositories")
    fun getGitHubRepository(
        @Query(Constants.LANGUAGE) language: String,
        @Query(Constants.SORT) sort: String,
        @Query(Constants.PAGE) page: Int
    ): Single<RepositoryResponse>

    @GET("repos/{ownerName}/{repositoryName}/pulls")
    fun getGitHubPullRequest(
        @Path(Constants.OWNER_NAME) language: String,
        @Path(Constants.REPOSITORY_NAME) sort: String,
        @Query(Constants.PAGE) page: Int
    ): Single<List<PullRequestResponse>>

}