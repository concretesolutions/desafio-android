package dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.model.data.PullRequest
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.repository.PullRequestRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

private const val INFORMATION = 1
private const val ERROR = 2
private const val EMPTY = 3

class PullRequestViewModel(private val pullRequestRepository: PullRequestRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val pullRequests = MutableLiveData<List<PullRequest>>()

    private val loadingProgressBar = MutableLiveData<Int>()

    fun getPullRequests(): LiveData<List<PullRequest>> = pullRequests

    fun getLoading(): LiveData<Int> = loadingProgressBar

    fun fetchPullRequests(username: String, repositoryName: String) {
        pullRequestRepository.fetchPullRequests(username, repositoryName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.isNotEmpty() }
            .doOnSuccess { loadingProgressBar.postValue(INFORMATION) }
            .doOnComplete { loadingProgressBar.postValue(EMPTY) }
            .subscribeBy(
                onSuccess = {
                    pullRequests.postValue(it)
                },
                onError = {
                    loadingProgressBar.postValue(ERROR)
                }
            ).addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}