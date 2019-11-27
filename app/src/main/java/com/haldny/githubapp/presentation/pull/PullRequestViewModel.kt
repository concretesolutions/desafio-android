package com.haldny.githubapp.presentation.pull

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haldny.githubapp.common.Resource
import com.haldny.githubapp.domain.model.ResponsePullRequest
import com.haldny.githubapp.domain.repository.IPullRequestRepository
import kotlinx.coroutines.launch

class PullRequestViewModel(private val owner: String, private val repository: String,
                           private val pullRequestRepository: IPullRequestRepository) : ViewModel() {

    private val pullRequestsLiveData
            = MutableLiveData<Resource<List<ResponsePullRequest>>>()

    init {
        fetchPullRequest()
    }

    fun getPullRequests(): LiveData<Resource<List<ResponsePullRequest>>>
            = pullRequestsLiveData

    private fun fetchPullRequest() {
        viewModelScope.launch {
            pullRequestsLiveData.loading()
            try {
                pullRequestsLiveData.success(
                    pullRequestRepository.getPullRequests(owner, repository)?.let { it })
            } catch (e: Exception) {
                pullRequestsLiveData.error(e)
            }
        }
    }

    fun refreshViewModel(){
        fetchPullRequest()
    }

    private fun <T> MutableLiveData<Resource<T>>.success(data: T?) {
        value = Resource.success(data)
    }

    private fun <T> MutableLiveData<Resource<T>>.error(t: Throwable?) {
        value = Resource.error(t)
    }

    private fun <T> MutableLiveData<Resource<T>>.loading() {
        value = Resource.loading()
    }
}