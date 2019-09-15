package com.desafioandroid.feature.pullrequest.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.desafioandroid.core.base.BaseViewModel
import com.desafioandroid.core.helper.Resource
import com.desafioandroid.data.model.pullrequest.entity.PullRequestResponse
import com.desafioandroid.feature.pullrequest.repository.PullRequestRepository
import kotlinx.coroutines.launch

class PullRequestViewModel(private val repository: PullRequestRepository) : BaseViewModel() {

    val getList = MutableLiveData<Resource<List<PullRequestResponse>>>()

    fun fetchPullRequest(userName: String, repositoryName: String) {
        viewModelScope.launch {
            getList.loading(true)
            try {
                getList.success(repository.getList(userName, repositoryName)?.let { it })
            } catch (e: Exception) {
                getList.error(e)
            } finally {
                getList.loading(false)
            }
        }
    }
}