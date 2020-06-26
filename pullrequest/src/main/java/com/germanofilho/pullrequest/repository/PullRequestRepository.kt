package com.germanofilho.pullrequest.repository

import com.germanofilho.network.feature.pullrequestlist.model.entity.GitPullRequestResponse

interface PullRequestRepository {
    suspend fun getPullRequestList(user: String, repo: String) : List<GitPullRequestResponse>
}