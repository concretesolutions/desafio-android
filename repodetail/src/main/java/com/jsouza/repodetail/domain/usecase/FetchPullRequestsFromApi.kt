package com.jsouza.repodetail.domain.usecase

import com.jsouza.repodetail.domain.repository.PullsRepository

class FetchPullRequestsFromApi(
    private val repository: PullsRepository
) {
    suspend operator fun invoke(userName: String, repoName: String, repositoryId: Long) = repository
        .refreshPullRequests(userName, repoName, repositoryId)
}
