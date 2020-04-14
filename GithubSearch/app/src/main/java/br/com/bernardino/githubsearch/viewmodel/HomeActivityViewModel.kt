package br.com.bernardino.githubsearch.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagedList
import br.com.bernardino.githubsearch.database.RepoSearchResult
import br.com.bernardino.githubsearch.database.RepositoryDatabase
import br.com.bernardino.githubsearch.repository.ReposRepositoryImpl

import kotlinx.coroutines.launch
import java.io.IOException

class HomeActivityViewModel(private val reposRepository: ReposRepositoryImpl) : ViewModel() {

    private val repositories = MutableLiveData<String>()
    private val repoResult: LiveData<RepoSearchResult> = Transformations.map(repositories) {
        reposRepository.refreshRepositories()
    }

    val repos: LiveData<PagedList<RepositoryDatabase>> = Transformations.switchMap(repoResult) { it -> it.data }
    val networkErrors: LiveData<String> = Transformations.switchMap(repoResult) { it ->
        it.networkErrors
    }

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        repositories.postValue("")
    }

    /*

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private var _networkErrorMessage = MutableLiveData("")
    val networkErrorMessage: LiveData<String>
        get() = networkErrorMessage

    private var nextPage = FIRST_PAGE

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        try {
            _isLoading.value = true
            repositories = reposRepository.refreshRepositories()
            _isNetworkErrorShown.value = false
            _isLoading.value = false

        } catch (networkError: IOException) {
            // Show a Toast error message and hide the progress bar.
            _isLoading.value = false
            if (repositories.value.isNullOrEmpty()) {
                _networkErrorMessage.value = reposRepository.boundaryCallback.networkErrors.value
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
    */

}