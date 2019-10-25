package com.example.eloyvitorio.githubjavapop.ui.pullrequest

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.eloyvitorio.githubjavapop.data.model.PullRequest
import com.example.eloyvitorio.githubjavapop.data.network.CallBackPullRequest
import com.example.eloyvitorio.githubjavapop.data.network.PullRequestsAPI

class PullRequestsViewModel(private val pullRequestsApi: PullRequestsAPI) : ViewModel() {
    var error = MutableLiveData<String>()
    var pullRequestList = MutableLiveData<List<PullRequest>>()

    fun fetchPullRequests(ownerLogin: String, repositoryName: String) {
        pullRequestsApi.listPullRequest(ownerLogin, repositoryName, object : CallBackPullRequest {
            override fun onSucessGetPullRequest(pullRequest: List<PullRequest>) {
                this@PullRequestsViewModel.pullRequestList.value = pullRequest
            }

            override fun onErrorGetPullRequest(message: String) {
                this@PullRequestsViewModel.error.value = message
            }
        })
    }
}