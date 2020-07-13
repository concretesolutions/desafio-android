package com.bassul.mel.app

import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.domain.PullRequest

interface AdapterPullRequestContract {

    fun addItems(list: List<PullRequest>)
}