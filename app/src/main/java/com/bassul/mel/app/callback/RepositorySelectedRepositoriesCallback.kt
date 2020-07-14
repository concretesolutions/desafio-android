package com.bassul.mel.app.callback

import com.bassul.mel.app.feature.pullRequestsList.repository.model.PullRequestListResponse

interface RepositorySelectedRepositoriesCallback {
    fun onSuccess(pullRequestList : List<PullRequestListResponse>)
    fun onError(s: String)
}
