package br.com.concrete.githubconcretechallenge.features.pullrequests.datasource

import br.com.concrete.githubconcretechallenge.features.pullrequests.model.PullRequestModel
import io.reactivex.Single

interface PullRequestsDataSource {

    fun getPullRequests(creatorName: String, repositoryName: String): Single<List<PullRequestModel>>

}