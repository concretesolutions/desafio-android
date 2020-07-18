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

    private val _pulls = MutableLiveData<List<PullsResponse>>()
    val returnPulls: LiveData<List<PullsResponse>>? = _pulls

    private fun pulls() {
        viewModelScope.launch(Dispatchers.IO) {
            _pulls.postValue(repoDetailService.loadPullsPageFromApiAsync(
                username = "elastic",
                repoName = "elasticsearch",
                page = 1,
                per_page = 20)
            )
        }
    }

    init {
        pulls()
    }
}
