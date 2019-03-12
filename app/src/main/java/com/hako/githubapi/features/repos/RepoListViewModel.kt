package com.hako.githubapi.features.repos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hako.githubapi.data.database.dao.RepositoryDao
import com.hako.githubapi.data.retrofit.NetworkStatus
import com.hako.githubapi.data.retrofit.NetworkStatus.*
import com.hako.githubapi.data.retrofit.RemoteDatasource
import com.hako.githubapi.domain.entities.Repository
import com.hako.githubapi.domain.requests.QueryRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.get
import timber.log.Timber

class RepoListViewModel(private val daoRepository: RepositoryDao) : ViewModel(), KoinComponent {

    val repositories = MutableLiveData<List<Repository>>()
    var networkStatus = MutableLiveData<NetworkStatus>()
    private val api: RemoteDatasource = get()
    private var index = INITIAL_PAGE
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
        networkStatus.value = Ready
        deleteRepos()
    }

    private fun deleteRepos() {
        subscription = Observable.fromCallable { daoRepository.nukeDatabase() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    index = INITIAL_PAGE
                    repositories.value = null //esto es un comentario no tan importante
                    getRepos()
                },
                { networkStatus.value = Errored }
            )
    }

    private fun getRepos() {
        subscription = Observable.fromCallable { daoRepository.getPage(index) }
            .concatMap { dbRepository ->
                // This is simple logic is good enough for the ocation, but a more desireable approach
                // would be to use some kind of paging.
                if (dbRepository.isEmpty() || daoRepository.count(index) == 0)
                    api.getRepositories(QueryRepository(page = index)).concatMap { repoList ->
                        daoRepository.saveAll(repoList)
                        Observable.just(repoList)
                    } else {
                    Observable.just(dbRepository)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { networkStatus.value = Loading }
            .doOnTerminate { networkStatus.value = Ready }
            .subscribe(
                { result ->
                    onRetrievedRepos(result)
                    index++
                },
                { e -> networkStatus.value = Errored }
            )
    }

    private fun onRetrievedRepos(repos: List<Repository>) {
        repositories.value = repositories.value?.plus(repos) ?: repos
    }
}
