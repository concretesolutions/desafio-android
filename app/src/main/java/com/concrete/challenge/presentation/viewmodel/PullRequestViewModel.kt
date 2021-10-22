package com.concrete.challenge.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.concrete.challenge.data.PullRequestEntity
import com.concrete.challenge.domain.io.APIService
import com.concrete.challenge.utils.callServicePullRequests

class PullRequestViewModel(private val service: APIService) : ViewModel() {

    val pullRequestsList: MutableLiveData<List<PullRequestEntity>> by lazy {
        MutableLiveData<List<PullRequestEntity>>()
    }

    fun getPullRequests(owner: String, repo: String) {
        callServicePullRequests(liveData = pullRequestsList) { service.getPullRequests(owner, repo) }
    }
}