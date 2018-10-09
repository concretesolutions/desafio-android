package br.com.repository.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import br.com.repository.model.PullRequest
import br.com.repository.model.Repository
import br.com.repository.service.request.PullRequestRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PullRequestViewModel : ViewModel() {
    private val pullRequestRequest = PullRequestRequest()

    private var lPullRequest = mutableListOf<PullRequest>()
    private var lPull = MutableLiveData<List<PullRequest>>()

    private var showProgress = MutableLiveData<Boolean>().apply {
        value = false
    }
    private var showLayoutNoMessage = MutableLiveData<Boolean>().apply {
        value = false
    }

    fun callPullRequest(repository: Repository) {
        if (lPullRequest.isEmpty()) {
            pullRequestRequest.getPullRequest(repository).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ pullRequest ->
                        lPullRequest.add(pullRequest)
                    }, {
                        showProgress.value = true
                        showLayoutNoMessage.value = true
                    }, {
                        if (lPullRequest.isNotEmpty()) {
                            lPull.value = lPullRequest
                            showProgress.value = true
                        } else {
                            showProgress.value = true
                            showLayoutNoMessage.value = true
                        }
                    })
        } else {
            lPull.value = lPullRequest
            showProgress.value = true
        }
    }

    fun getPull(): LiveData<List<PullRequest>> = lPull
    fun showProgress(): LiveData<Boolean> = showProgress
    fun showLayoutNoMessage(): LiveData<Boolean> = showLayoutNoMessage

}