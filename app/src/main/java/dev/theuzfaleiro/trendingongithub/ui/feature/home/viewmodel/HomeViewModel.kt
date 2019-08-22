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

private const val INFORMATION = 0
private const val LOADING = 1
private const val ERROR = 2

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val repositories = MutableLiveData<PagedList<Repository>>()

    private val loadingProgressBar = MutableLiveData<Int>()

    fun getRepositories(): LiveData<PagedList<Repository>> = repositories

    fun getLoading(): LiveData<Int> = loadingProgressBar

    fun fetchRepositories() {
        homeRepository.fetchTrendingRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter {
                loadingProgressBar.postValue(ERROR)
                it.isNotEmpty()
            }
            .doOnSubscribe { loadingProgressBar.postValue(LOADING) }
            .doOnNext { loadingProgressBar.postValue(INFORMATION) }
            .doOnError { loadingProgressBar.postValue(ERROR) }
            .subscribeBy(
                onNext = {
                    repositories.postValue(it)
                },
                onError = {
                    loadingProgressBar.postValue(ERROR)
                }).addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}