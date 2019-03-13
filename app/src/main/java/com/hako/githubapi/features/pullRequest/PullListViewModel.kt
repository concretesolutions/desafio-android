package com.hako.githubapi.features.pullRequest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hako.githubapi.data.retrofit.NetworkStatus
import com.hako.githubapi.data.retrofit.NetworkStatus.*
import com.hako.githubapi.domain.entities.PullRequest
import com.hako.githubapi.domain.requests.QueryPullRequest
import com.hako.githubapi.domain.usecases.GetPullRequests
import io.reactivex.disposables.Disposable
import org.koin.standalone.KoinComponent
import org.koin.standalone.get
import timber.log.Timber

class PullListViewModel : ViewModel(), KoinComponent {

    val pullRequests = MutableLiveData<List<PullRequest>>()
    var networkStatus = MutableLiveData<NetworkStatus>()
    private val getPullRequest: GetPullRequests = get()
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
        subscription = getPullRequest.execute(query)
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
