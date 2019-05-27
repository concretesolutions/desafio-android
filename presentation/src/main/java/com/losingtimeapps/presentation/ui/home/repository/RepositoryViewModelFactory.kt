package com.losingtimeapps.presentation.ui.home.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.losingtimeapps.domain.usercase.GetGitHubRepoUseCase

class RepositoryViewModelFactory(
    private val getGitHubRepoUseCase: GetGitHubRepoUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepositoryViewModel(
            getGitHubRepoUseCase
        ) as T
    }
}