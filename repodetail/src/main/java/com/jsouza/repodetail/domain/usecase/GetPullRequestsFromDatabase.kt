package com.jsouza.repodetail.domain.usecase

import androidx.lifecycle.LiveData
import com.jsouza.repodetail.domain.model.PullRequests
import com.jsouza.repodetail.domain.repository.PullsRepository

class GetPullRequestsFromDatabase(
    private val repository: PullsRepository
) {
    operator fun invoke(repositoryId: Long): LiveData<List<PullRequests>?> = repository
        .getPullRequests(repositoryId = repositoryId)
}
