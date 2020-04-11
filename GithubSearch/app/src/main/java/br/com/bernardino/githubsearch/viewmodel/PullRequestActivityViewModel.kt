package br.com.bernardino.githubsearch.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import br.com.bernardino.githubsearch.model.PullRequest
import br.com.bernardino.githubsearch.repository.ReposRepository
import br.com.bernardino.githubsearch.repository.ReposRepositoryImpl
import kotlinx.coroutines.launch
import java.io.IOException

class PullRequestActivityViewModel(
    private val reposRepository : ReposRepository,
    creator: String,
    repository: String
) : ViewModel() {

    private var pullRequest : List<PullRequest> = listOf()

    private var _pullRequestLiveData = MutableLiveData<List<PullRequest>>()
    val pullRequestLiveData : LiveData<List<PullRequest>>
        get() = _pullRequestLiveData

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        readPullRequestAPI(creator, repository)
    }

    private fun readPullRequestAPI (creator: String, repository: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                pullRequest = reposRepository.getPullRequest(creator, repository)
                _pullRequestLiveData.value = pullRequest
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
                _isLoading.value = false

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                _isLoading.value = false
                if(pullRequestLiveData.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}