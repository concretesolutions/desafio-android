package com.losingtimeapps.presentation.ui.home.repository

import com.losingtimeapps.presentation.model.RepositoryModel
import com.losingtimeapps.common.BaseView

interface RepositoryView : BaseView {

    fun updateRepoLiveData(repoListData: List<RepositoryModel>)

    fun navigateToPullRequestView(ownerName: String, repoName: String)
}