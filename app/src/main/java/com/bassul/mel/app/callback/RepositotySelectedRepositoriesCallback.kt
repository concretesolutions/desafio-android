package com.bassul.mel.app.callback

import com.bassul.mel.app.feature.repositoriesList.repository.model.PullRequestListResponse

interface RepositotySelectedRepositoriesCallback {
    fun onSuccess(pullRequestList : List<PullRequestListResponse>)
}
