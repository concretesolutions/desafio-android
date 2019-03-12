package com.hako.githubapi.features.repos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hako.githubapi.data.retrofit.NetworkStatus
import com.hako.githubapi.data.retrofit.NetworkStatus.*
import com.hako.githubapi.domain.entities.Repository
import com.hako.githubapi.domain.usecases.DeleteRepositories
import com.hako.githubapi.domain.usecases.GetRepositories
import io.reactivex.disposables.Disposable
import org.koin.standalone.KoinComponent
import org.koin.standalone.get
import timber.log.Timber

class RepoListViewModel : ViewModel(), KoinComponent {

    val repositories = MutableLiveData<List<Repository>>()
    var networkStatus = MutableLiveData<NetworkStatus>()

    private var index = INITIAL_PAGE
    private val getRepositories: GetRepositories = get()
    private val deleteRepositories: DeleteRepositories = get()
    private lateinit var subscription: Disposable

    companion object {
        const val INITIAL_PAGE = 1
    }

    init {
        networkStatus.value = Ready
    }

    fun loadRepositories() {
        when (networkStatus.value) {
            Ready -> getRepos()
            Loading -> Timber.d("There's a thread running")
        }
    }

    fun refreshRepositories() {
        when (networkStatus.value) {
            Ready -> deleteRepos()
            Errored -> deleteRepos()
            Loading -> Timber.d("There's a thread running")
        }
    }

    private fun deleteRepos() {
        subscription = deleteRepositories.execute()
            .subscribe(
                {
                    index = INITIAL_PAGE
                    repositories.value = null
                    getRepos()
                },
                { networkStatus.value = Errored }
            )
    }

    private fun getRepos() {
        subscription = getRepositories.execute(index)
            .doOnSubscribe { networkStatus.value = Loading }
            .doOnTerminate { networkStatus.value = Ready }
            .subscribe(
                { result ->
                    onRetrievedRepos(result)
                    index++
                },
                { networkStatus.value = Errored }
            )
    }

    private fun onRetrievedRepos(repos: List<Repository>) {
        repositories.value = repositories.value?.plus(repos) ?: repos
    }
}
