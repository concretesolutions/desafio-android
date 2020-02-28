package br.com.bernardino.githubsearch.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.bernardino.githubsearch.ClickReposListener
import br.com.bernardino.githubsearch.database.getDatabase
import br.com.bernardino.githubsearch.repository.ReposRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

import kotlinx.coroutines.launch
import java.io.IOException

class HomeActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val reposRepository = ReposRepository(getDatabase(application))

    private val viewModelJob = SupervisorJob()

    val repolist = reposRepository.repos

    var clickReposListener : ClickReposListener? = null

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var isLoading = MutableLiveData<Boolean>()

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                isLoading.value = true
                reposRepository.refreshRepositories()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
                isLoading.value = false

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                isLoading.value = false
                if(repolist.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }


    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}