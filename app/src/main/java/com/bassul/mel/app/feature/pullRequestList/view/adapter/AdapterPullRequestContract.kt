package com.bassul.mel.app.feature.pullRequestList.view.adapter

import com.bassul.mel.app.domain.PullRequest

interface AdapterPullRequestContract {

    fun addItems(list: List<PullRequest>)
}