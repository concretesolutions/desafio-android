package com.losingtimeapps.presentation.ui.home.pullrequest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.losingtimeapps.domain.usercase.GetGitHubPullRequestsUseCase

class PullRequestViewModelFactory(
    private val getGitHubPullRequestsUseCase: GetGitHubPullRequestsUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PullRequestViewModel(
            getGitHubPullRequestsUseCase
        ) as T
    }
}