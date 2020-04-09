package br.com.bernardino.githubsearch.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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


    private val reposRepository = ReposRepository(getDatabase(application))

    private var pullRequest : List<PullRequest> = listOf()

    var pullRequestLiveData = MutableLiveData<List<PullRequest>>()

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

    fun readPullRequestAPI (creator: String, repository: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                Log.i("Bernardino", "Entrou launch API REQUEST")
                pullRequest = reposRepository.getPullRequest(creator, repository)
                pullRequestLiveData.value = pullRequest
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