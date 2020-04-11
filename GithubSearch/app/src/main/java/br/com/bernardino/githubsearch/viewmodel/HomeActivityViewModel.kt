package br.com.bernardino.githubsearch.viewmodel

import androidx.lifecycle.*
import br.com.bernardino.githubsearch.database.RepositoryDatabase
import br.com.bernardino.githubsearch.repository.ReposRepository
import br.com.bernardino.githubsearch.repository.ReposRepositoryImpl

import kotlinx.coroutines.launch
import java.io.IOException

class HomeActivityViewModel(private val reposRepository : ReposRepositoryImpl) : ViewModel() {

    var repositories = reposRepository.repos

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private var nextPage = FIRST_PAGE

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                reposRepository.refreshRepositories(1)
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
                _isLoading.value = false
                nextPage++

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                _isLoading.value = false
                if(repositories.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}