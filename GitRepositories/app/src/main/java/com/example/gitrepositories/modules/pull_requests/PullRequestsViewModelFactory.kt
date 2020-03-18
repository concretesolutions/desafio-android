package com.example.gitrepositories.modules.pull_requests

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PullRequestsViewModelFactory(private val application: Application, private val repoName: String, private val repoCreator: String)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PullRequestsViewModel(application, repoName, repoCreator) as T
    }
}