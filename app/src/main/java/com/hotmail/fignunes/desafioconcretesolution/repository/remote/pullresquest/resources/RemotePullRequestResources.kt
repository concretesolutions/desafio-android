package com.hotmail.fignunes.desafioconcretesolution.repository.remote.pullresquest.resources

import com.hotmail.fignunes.desafioconcretesolution.repository.remote.pullresquest.responses.PullRequestResponses
import io.reactivex.Single
import retrofit2.Response

interface RemotePullRequestResources {
    fun getPullRequest(login: String, repository: String): Single<Response<List<PullRequestResponses>>>
}