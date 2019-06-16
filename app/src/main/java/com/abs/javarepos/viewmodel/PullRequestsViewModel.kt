package com.abs.javarepos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.abs.javarepos.model.PullRequest
import com.abs.javarepos.model.Repo
import com.abs.javarepos.model.repository.PullRequestRepository

class PullRequestsViewModel : ViewModel() {

    private val pullRequestRepository = PullRequestRepository()
    val networkError: LiveData<Boolean> = pullRequestRepository.networkError

    fun fetchPullRequests(repo: Repo): LiveData<ArrayList<PullRequest>> = pullRequestRepository.getPullRequests(repo)
}