package com.concrete.andresdavid.desafioandroid.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.concrete.andresdavid.desafioandroid.model.PullRequest
import com.concrete.andresdavid.desafioandroid.model.Resource
import com.concrete.andresdavid.desafioandroid.repository.PullRequestRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class PullRequestViewModel : ViewModel() {
    private val pullRequestRepo: PullRequestRepository= PullRequestRepository()
    private lateinit var pullRequestsData: MutableLiveData<Resource<List<PullRequest>>>

    fun getPullRequests(repoName: String): LiveData<Resource<List<PullRequest>>> {
        if (!::pullRequestsData.isInitialized) {
            pullRequestsData = MutableLiveData()
            load(repoName)
        }
        return pullRequestsData
    }

    fun load(repoName: String) {
        pullRequestRepo.getPullRequestsByRepository(repoName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    addItems(result)
                }, { error ->
                    this.pullRequestsData.value = Resource.error(error.message!!, mutableListOf())
                })
    }

    fun addItems(newItems: List<PullRequest>){
        this.pullRequestsData.value = Resource.success(newItems)
    }

}