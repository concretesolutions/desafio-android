package com.bassul.mel.app.feature.pullRequestsList.view.adapter

import com.bassul.mel.app.domain.PullRequest

interface AdapterPullRequestContract {
    fun addItems(newItems: List<PullRequest>)
}