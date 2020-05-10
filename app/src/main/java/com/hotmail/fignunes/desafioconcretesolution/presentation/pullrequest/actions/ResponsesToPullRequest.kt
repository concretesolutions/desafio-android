package com.hotmail.fignunes.desafioconcretesolution.presentation.pullrequest.actions

import com.hotmail.fignunes.desafioconcretesolution.model.PullRequest
import com.hotmail.fignunes.desafioconcretesolution.model.PullRequestUser
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.pullresquest.responses.PullRequestResponses

class ResponsesToPullRequest {
    fun execute(responses: List<PullRequestResponses>): List<PullRequest> {
        return responses.map {
            PullRequest(
                it.id,
                it.title,
                PullRequestUser(
                    it.user.login,
                    it.user.id,
                    it.user.avatar_url,
                    it.user.url,
                    "",
                    ""
                ),
                it.body,
                it.created_at,
                it.state,
                it.html_url
            )
        }
    }
}