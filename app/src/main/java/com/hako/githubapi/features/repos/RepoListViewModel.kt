package com.hako.githubapi.features.repos

import androidx.lifecycle.ViewModel
import com.hako.githubapi.data.repository.database.dao.RepositoryDao
import com.hako.githubapi.data.repository.retrofit.RetrofitDatasource
import com.hako.githubapi.domain.entities.Repositories
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

    private val api: RetrofitDatasource = get()

    private lateinit var subscription: Disposable

    fun loadRepositories() {
        subscription = Observable.fromCallable { daoRepository.all }
            .concatMap { dbRepository ->
                if (dbRepository.isEmpty())
                    api.getRepositories(QueryRepository()).concatMap { repoList ->
                        daoRepository.saveAll(repoList)
                        Observable.just(repoList)
                    } else {
                    Observable.just(dbRepository)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { Timber.w("Loading started") }
            .doOnTerminate { Timber.w("Loading finished") }
            .subscribe(
                { result -> onRetrievedRepos(result) },
                { e -> Timber.e("Error loading: ${e.localizedMessage}") }
            )
    }

    private fun onRetrievedRepos(repos: List<Repository>) {
        repos.forEach {
            Timber.d(it.name)
        }
    }
}
