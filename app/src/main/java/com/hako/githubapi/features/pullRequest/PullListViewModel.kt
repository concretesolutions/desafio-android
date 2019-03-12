package com.hako.githubapi.features.pullRequest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hako.githubapi.data.retrofit.NetworkStatus
import com.hako.githubapi.data.retrofit.NetworkStatus.*
import com.hako.githubapi.data.retrofit.RemoteDatasource
import com.hako.githubapi.domain.entities.PullRequest
import com.hako.githubapi.domain.requests.QueryPullRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.get
import timber.log.Timber

class PullListViewModel : ViewModel(), KoinComponent {

    val pullRequests = MutableLiveData<List<PullRequest>>()
    var networkStatus = MutableLiveData<NetworkStatus>()
    private val api: RemoteDatasource = get()
    private lateinit var subscription: Disposable

    init {
        networkStatus.value = Ready
    }

    fun loadPullRequests(query: QueryPullRequest) {
        when (networkStatus.value) {
            Ready -> getPulls(query)
            Loading -> Timber.d("There's a thread running")
        }
    }

    private fun getPulls(query: QueryPullRequest) {
        subscription = api.getPullsRequests(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { networkStatus.value = Loading }
            .doOnTerminate { networkStatus.value = Ready }
            .subscribe(
                { result -> onRetrievedPulls(result) },
                { networkStatus.value = Errored }
            )
    }

    private fun onRetrievedPulls(pulls: List<PullRequest>) {
        pullRequests.value = pulls
    }
}
