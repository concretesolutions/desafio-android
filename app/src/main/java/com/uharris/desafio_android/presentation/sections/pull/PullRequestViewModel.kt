package com.uharris.desafio_android.presentation.sections.pull

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uharris.desafio_android.data.base.Failure
import com.uharris.desafio_android.data.base.Result
import com.uharris.desafio_android.domain.models.PullRequest
import com.uharris.desafio_android.domain.models.Repository
import com.uharris.desafio_android.domain.usecases.actions.FetchPullRequest
import com.uharris.desafio_android.domain.usecases.actions.FetchRepositories
import com.uharris.desafio_android.presentation.state.Resource
import com.uharris.desafio_android.presentation.state.ResourceState
import javax.inject.Inject

class PullRequestViewModel @Inject constructor(
    private val fetchPullRequest: FetchPullRequest,
    application: Application
): AndroidViewModel(application) {

    val pullRequestsLiveData: MutableLiveData<Resource<List<PullRequest>>> = MutableLiveData()

    fun fetchPullRequest(creator: String, repository: String) {
        pullRequestsLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        fetchPullRequest(FetchPullRequest.Params(creator, repository)){
            when(it){
                is Result.Success -> handleRepositories(it.data)
                is Result.Error -> handleError(it.failure)
            }
        }
    }

    private fun handleRepositories(repositories: List<PullRequest>) {

        pullRequestsLiveData.postValue(Resource(ResourceState.SUCCESS, repositories, null))
    }

    private fun handleError(failure: Failure) {
        val message = when (failure) {
            is Failure.NetworkConnection -> "Error with network connection"
            else -> "Error with the server."
        }

        pullRequestsLiveData.postValue(Resource(ResourceState.ERROR, null, message))
    }
}