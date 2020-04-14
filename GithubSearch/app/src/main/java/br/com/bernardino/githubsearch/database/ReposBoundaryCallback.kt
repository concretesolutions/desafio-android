package br.com.bernardino.githubsearch.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import br.com.bernardino.githubsearch.network.GithubApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ReposBoundaryCallback
    (
    private val api: GithubApi,
    private val dao: ReposDao
) : PagedList.BoundaryCallback<RepositoryDatabase>() {
    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 1

    private val _networkErrors = MutableLiveData<String>()

    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    suspend fun loadRepositories() {
        try {
            isRequestInProgress = true
            val reposList = api.getRepositories(lastRequestedPage)
            dao.insertAll(reposList.items.asDomainModel())
            lastRequestedPage++
        } catch (e : Exception) {
            _networkErrors.postValue(e.message)
        }
    }

    override fun onZeroItemsLoaded() {
        CoroutineScope(Dispatchers.IO).launch {
            loadRepositories()
            isRequestInProgress = false
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: RepositoryDatabase) {
        CoroutineScope(Dispatchers.IO).launch {
            loadRepositories()
            isRequestInProgress = false
        }
    }
}