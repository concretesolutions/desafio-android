package br.com.concrete.githubconcretechallenge.features.pullrequests.datasource

import br.com.concrete.githubconcretechallenge.features.pullrequests.model.PullRequestModel
import br.com.concrete.githubconcretechallenge.features.pullrequests.service.PullRequestsRetrofit
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class PullRequestsRemoteDataSource(private val pullRequestsRetrofit: PullRequestsRetrofit) : PullRequestsDataSource {

    override fun getPullRequests(creatorName: String, repositoryName: String): Single<List<PullRequestModel>> {
        return pullRequestsRetrofit.getPullRequests(creatorName, repositoryName)
            .observeOn(AndroidSchedulers.mainThread())
    }

}