package com.concretesolutions.desafioandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.concretesolutions.desafioandroid.helpers.NetworkHelper
import com.concretesolutions.desafioandroid.model.PullRequest
import com.concretesolutions.desafioandroid.service.RepositoryService
import javax.security.auth.callback.Callback

class PullRequestViewModel {

    private var pullRequests: MutableLiveData<MutableList<PullRequest>> = MutableLiveData()
    private val repositoriesService = NetworkHelper.getRetrofitInstanceGitHub()
        .create(RepositoryService::class.java)

    fun getPullRequests() = pullRequests

    fun loadPulls(owner: String, repo: String) {
        repositoriesService.getRepositoryPulls(owner, repo)
            .enqueue(object : Callback<List<PullRequest>!>!{

            })
    }
}