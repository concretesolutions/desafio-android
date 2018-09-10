package com.concrete.desafioandroid.features.pulls

import com.concrete.desafioandroid.data.models.PullRequest
import com.concrete.desafioandroid.features.base.BaseView

interface PullsView: BaseView {
    fun onGetPullsRequests(pulls: List<PullRequest>)
    fun updateUi(opened: Int, closed: Int)
}