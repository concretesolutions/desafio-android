package com.jsouza.repodetail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsouza.repodetail.data.RepoDetailService
import com.jsouza.repodetail.data.remote.response.PullsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepoDetailViewModel(
    private val repoDetailService: RepoDetailService
) : ViewModel() {

    private val _pullRequests = MutableLiveData<List<PullsResponse>>()
    val returnPulls: LiveData<List<PullsResponse>>? = _pullRequests

    fun fetchPullRequests(
        userName: String?,
        repoName: String?
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (userName != null && repoName != null) {
                _pullRequests.postValue(repoDetailService.loadPullsPageFromApiAsync(
                    username = userName,
                    repoName = repoName)
                )
            }
        }
    }
}
