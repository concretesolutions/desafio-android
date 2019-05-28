package com.losingtimeapps.presentation.mapper

import com.losingtimeapps.domain.entity.PullRequest
import com.losingtimeapps.domain.entity.Repository
import com.losingtimeapps.presentation.model.AuthorModel
import com.losingtimeapps.presentation.model.PullRequestModel
import com.losingtimeapps.presentation.model.RepositoryModel

class PullRequestModelMapper {

    fun apply(repositoryList: List<PullRequest>): List<PullRequestModel> {

        return repositoryList.map {
            PullRequestModel(
                it.id,
                it.title,
                it.data,
                it.body,
                AuthorModel(
                    it.author.id,
                    it.author.name,
                    it.author.photoUrl
                ),
                it.pullRequestUrl,
                it.state
            )
        }
    }
}