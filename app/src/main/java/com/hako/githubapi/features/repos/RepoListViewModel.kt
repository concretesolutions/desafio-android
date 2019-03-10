package com.hako.githubapi.features.repos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import com.hako.githubapi.data.database.dao.RepositoryDao
import com.hako.githubapi.data.retrofit.RemoteDatasource
import com.hako.githubapi.domain.entities.Repository
import com.hako.githubapi.domain.requests.QueryRepository
import com.hako.githubapi.util.NetworkStatus
import com.hako.githubapi.util.NetworkStatus.Loading
import com.hako.githubapi.util.NetworkStatus.Ready
import com.hako.githubapi.util.RepoDiffUtilCallback
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.get
import timber.log.Timber

class RepoListViewModel(private val daoRepository: RepositoryDao) : ViewModel(), KoinComponent {

    private val api: RemoteDatasource = get()

    private val repositories = MutableLiveData<List<Repository>>()
    private var index = 1
    private var networkStatus: NetworkStatus = Ready
    private lateinit var subscription: Disposable

    fun loadRepositories() {
        when (networkStatus) {
            Ready -> getRepos()
            Loading -> Timber.d("There's a thread running")
        }

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
            .doOnSubscribe { networkStatus = Loading }
            .doOnTerminate { networkStatus = Ready }
            .subscribe(
                { result ->
                    onRetrievedRepos(result)
                    index++
                },
                { e -> Timber.e("Error loading: ${e.localizedMessage}") }
            )
    }

    private fun onRetrievedRepos(repos: List<Repository>) {
        val diffResult = DiffUtil.calculateDiff(RepoDiffUtilCallback(repos, repositories))
        repositories.value = repos
        repos.forEach {
            Timber.d(it.name)
        }
    }
}
