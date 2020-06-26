package com.germanofilho.pullrequest.repository

import com.germanofilho.network.factory.ApiFactory
import com.germanofilho.network.feature.pullrequestlist.data.source.remote.api.GitPullRequestApi
import com.germanofilho.network.feature.pullrequestlist.model.entity.GitPullRequestResponse

class PullRequestRepositoryImpl : PullRequestRepository {

    private val service by lazy { ApiFactory.request(GitPullRequestApi::class.java) }

    override suspend fun getPullRequestList(user: String, repo: String): List<GitPullRequestResponse> {
        return service.getPullRequestList(user = user, repo = repo)
    }
}