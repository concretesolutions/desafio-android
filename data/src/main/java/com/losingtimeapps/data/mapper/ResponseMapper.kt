package com.losingtimeapps.data.mapper

import com.losingtimeapps.data.model.PullRequest.PullRequestResponse
import com.losingtimeapps.data.model.RepositoryResponse
import com.losingtimeapps.domain.entity.Author
import com.losingtimeapps.domain.entity.PullRequest
import com.losingtimeapps.domain.entity.Repository

class ResponseMapper {

    fun apply(pullRequestResponseList: List<PullRequestResponse>): List<PullRequest> {

        return pullRequestResponseList.map {
            PullRequest(
                it.id,
                it.title,
                it.body,
                it.body,
                Author(
                    it.user.id,
                    it.user.login,
                    it.user.avatarUrl
                ),
                it.htmlUrl
            )
        }
    }

    fun apply(repositoryResponseList: RepositoryResponse): List<Repository> {

        return repositoryResponseList.items.map {
            Repository(
                it.id,
                it.name,
                it.description,
                it.stargazersCount,
                it.forksCount,
                Author(
                    it.owner.id,
                    it.owner.login,
                    it.owner.avatarUrl
                )
            )
        }
    }
}