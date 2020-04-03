package br.com.bernardino.githubsearch.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.bernardino.githubsearch.database.getDatabase
import br.com.bernardino.githubsearch.model.PullRequest
import br.com.bernardino.githubsearch.repository.ReposRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException

class PullRequestActivityViewModel(
    application: Application,
    creator: String,
    repository: String
) : AndroidViewModel(application) {
    private val viewModelJob = SupervisorJob()

    private val reposRepository = ReposRepository(getDatabase(application))

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var pullRequest : List<PullRequest> = listOf()

    var pullRequestLiveData = MutableLiveData<List<PullRequest>>()

    var isLoading = MutableLiveData<Boolean>()

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        readPullRequestAPI(creator, repository)
    }

    fun readPullRequestAPI (creator: String, repository: String) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                Log.i("Bernardino", "Entrou launch API REQUEST")
                pullRequest = reposRepository.getPullRequest(creator, repository)
                pullRequestLiveData.value = pullRequest
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
                isLoading.value = false

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                isLoading.value = false
                if(pullRequestLiveData.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}