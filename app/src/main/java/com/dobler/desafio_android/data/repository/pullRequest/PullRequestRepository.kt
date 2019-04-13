package com.dobler.desafio_android.data.repository.pullRequest

import androidx.annotation.MainThread
import com.dobler.desafio_android.data.api.repositoryPullRequest.RepositoryPullRequestService
import com.dobler.desafio_android.data.model.RepositoryPullRequest
import io.reactivex.Observable

class PullRequestRepository(private val api: RepositoryPullRequestService) :
    PullRequestRepositoryContract {


    @MainThread
    override fun getAll(user: String, repositoryName: String): Observable<List<RepositoryPullRequest>> {
        return api.getList(user, repositoryName)
    }
}