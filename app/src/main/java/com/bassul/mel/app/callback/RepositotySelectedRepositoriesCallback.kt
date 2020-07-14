package com.bassul.mel.app.callback

import com.bassul.mel.app.feature.pullRequestList.repository.model.PullRequestListResponse

interface RepositotySelectedRepositoriesCallback {
    fun onSuccess(pullRequestList : List<PullRequestListResponse>)
    fun onError(s: String)
}
