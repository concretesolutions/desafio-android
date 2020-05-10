package com.hotmail.fignunes.desafioconcretesolution.repository.remote.pullresquest

import com.hotmail.fignunes.desafioconcretesolution.repository.remote.pullresquest.resources.RemotePullRequestResources
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.pullresquest.responses.PullRequestResponses
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.pullresquest.services.PullRequestServices
import io.reactivex.Single
import retrofit2.Response

class RemotePullRequestRepository(private val pullRequestServices: PullRequestServices) :
    RemotePullRequestResources {
    override fun getPullRequest(login: String, repository: String): Single<Response<List<PullRequestResponses>>> =
        pullRequestServices.getPullRequest(login, repository)
}