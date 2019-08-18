package dev.theuzfaleiro.trendingongithub.ui.feature.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dev.theuzfaleiro.trendingongithub.ui.feature.home.model.data.Repository
import dev.theuzfaleiro.trendingongithub.ui.feature.home.repository.HomeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val repositories: MutableLiveData<PagedList<Repository>> by lazy {
        MutableLiveData<PagedList<Repository>>().also {
            fetchRepositories()
        }
    }

    private fun fetchRepositories() {
        homeRepository.fetchTrendingRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    repositories.postValue(it)
                },
                onError = {
                    TODO("Handle Error For Loading Repositories")
                }
            ).addTo(compositeDisposable)
    }

    fun getRepositories(): LiveData<PagedList<Repository>> = repositories

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}