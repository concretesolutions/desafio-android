package br.com.concrete.githubconcretechallenge.features.pullrequests.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.com.concrete.githubconcretechallenge.features.pullrequests.datasource.PullRequestsDataSource
import br.com.concrete.githubconcretechallenge.features.pullrequests.model.PullRequestModel
import br.com.concrete.githubconcretechallenge.features.repositories.model.RepositoryModel
import br.com.concrete.githubconcretechallenge.util.forceSetValue
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class PullRequestsViewModel(private val pullRequestsDataSource: PullRequestsDataSource) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _liveDataPullRequestList = MutableLiveData<List<PullRequestModel>>()
    val liveDataPullRequestList: LiveData<List<PullRequestModel>>
        get() {
            return _liveDataPullRequestList
        }

    val liveDataOpenedClosedPullRequestCount: LiveData<Pair<Int, Int>> =
        Transformations.map(_liveDataPullRequestList) { pullRequestList ->
            val openedPrs = pullRequestList.count { pr -> pr.state == "open" }
            val closedPrs = pullRequestList.size - openedPrs
            openedPrs to closedPrs
        }

    private val _liveDataRequestFailed = MutableLiveData<Boolean>()
    val liveDataRequestFailed : LiveData<Boolean>
        get() {
            return _liveDataRequestFailed
        }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun loadData(repositoryModel: RepositoryModel, invalidateCache: Boolean = false) {

        val onSuccess = fun(response: List<PullRequestModel>) {
            _liveDataPullRequestList.value = response
            _liveDataRequestFailed.value = false
        }

        val onFailure = fun(error: Throwable) {
            Log.d("errorRepo", error.localizedMessage)
            _liveDataRequestFailed.value = true
            _liveDataRequestFailed.forceSetValue()
        }

        addDisposable(
            pullRequestsDataSource.getPullRequests(repositoryModel.owner.login, repositoryModel.name, invalidateCache)
                .subscribe(onSuccess, onFailure)
        )

    }

    fun loadPullRequests(repositoryModel: RepositoryModel) {
        loadData(repositoryModel)
    }

    fun refreshPullRequests(repositoryModel: RepositoryModel) {
        loadData(repositoryModel, true)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }

}
