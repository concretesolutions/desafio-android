package br.com.concrete.githubconcretechallenge.features.pullrequests.service

import br.com.concrete.githubconcretechallenge.features.pullrequests.model.PullRequestModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestsRetrofit {

    @GET("repos/{creator_name}/{repository_name}/pulls")
    fun getPullRequests(
        @Path(value = "creator_name", encoded = true) creatorName: String,
        @Path(value = "repository_name", encoded = true) repositoryName: String
    ) : Single<List<PullRequestModel>>


}
