package com.hotmail.fignunes.desafioconcretesolution.presentation.pullrequest.actions

import com.hotmail.fignunes.desafioconcretesolution.common.exceptions.EmptyReturnException
import com.hotmail.fignunes.desafioconcretesolution.repository.Repository
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.pullresquest.responses.PullRequestResponses
import io.reactivex.Single
import retrofit2.HttpException

class GetPullRequest(private val repository: Repository) {

    fun execute(login: String, repositoryPr: String): Single<List<PullRequestResponses>> {
        return repository.remote.pullRequest.getPullRequest(login, repositoryPr)
            .map {
                when (it.code()) {
                    200 -> it?.body() ?: throw EmptyReturnException()
                    else -> throw HttpException(it)
                }
            }
    }
}