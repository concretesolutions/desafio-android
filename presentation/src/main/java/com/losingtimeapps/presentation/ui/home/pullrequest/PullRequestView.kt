package com.losingtimeapps.presentation.ui.home.pullrequest

import com.losingtimeapps.common.BaseView
import com.losingtimeapps.presentation.model.PullRequestModel

interface PullRequestView : BaseView {

    fun updateRepoLiveData(pullRequestListData: List<PullRequestModel>)

    fun navigateToPullRequestView(pullRequestUrl:String)
}