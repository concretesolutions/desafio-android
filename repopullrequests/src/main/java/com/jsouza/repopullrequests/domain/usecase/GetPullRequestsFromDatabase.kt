package com.jsouza.repopullrequests.domain.usecase

import androidx.lifecycle.LiveData
import com.jsouza.repopullrequests.domain.model.PullRequests
import com.jsouza.repopullrequests.domain.repository.PullsRepository

class GetPullRequestsFromDatabase(
    private val repository: PullsRepository
) {
    operator fun invoke(
        repositoryId: Long
    ): LiveData<List<PullRequests>?> = repository.getPullRequests(repositoryId = repositoryId)
}
