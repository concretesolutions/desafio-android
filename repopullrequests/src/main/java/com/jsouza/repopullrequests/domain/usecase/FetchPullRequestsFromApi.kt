package com.jsouza.repopullrequests.domain.usecase

import com.jsouza.repopullrequests.domain.repository.PullsRepository

class FetchPullRequestsFromApi(
    private val repository: PullsRepository
) {
    suspend operator fun invoke(
        userName: String,
        repoName: String,
        repoId: Long
    ) = repository.refreshPullRequests(userName, repoName, repoId)
}
