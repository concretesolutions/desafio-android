package com.germanofilho.pullrequest.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.germanofilho.core.helper.Resource
import com.germanofilho.core.viewmodel.BaseViewModel
import com.germanofilho.network.feature.pullrequestlist.model.entity.GitPullRequestResponse
import com.germanofilho.pullrequest.repository.PullRequestRepository
import kotlinx.coroutines.launch

class PullRequestViewModel(private val repository: PullRequestRepository) : BaseViewModel(){

    val pullRequestList = MutableLiveData<Resource<List<GitPullRequestResponse>>>()

    fun getPullRequestList(user: String, repo: String) {
        viewModelScope.launch {
            pullRequestList.loading(true)
            try {
                pullRequestList.success(repository.getPullRequestList(user, repo))
            } catch (e: Exception) {
                pullRequestList.error(e)
            } finally {
                pullRequestList.loading(false)
            }
        }
    }
}