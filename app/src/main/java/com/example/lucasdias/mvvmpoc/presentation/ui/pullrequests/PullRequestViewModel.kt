package com.example.lucasdias.mvvmpoc.presentation.ui.pullrequests

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.example.lucasdias.mvvmpoc.domain.entity.PullRequest
import com.example.lucasdias.mvvmpoc.domain.useCase.PullRequestUseCase

class PullRequestViewModel(application: Application, private val useCase: PullRequestUseCase): AndroidViewModel(application) {

    fun pullRequestList(repositoryId: String): LiveData<List<PullRequest>>? {
        return useCase.getPullRequestList(repositoryId)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun loadRepositoryPageFromApi(repositoryFullName: String, repositoryId: String) {
        useCase.loadPullRequestsFromApi(repositoryFullName, repositoryId)
    }

}