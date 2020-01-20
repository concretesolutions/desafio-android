package com.concretesolutions.desafioandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.concretesolutions.desafioandroid.R
import com.concretesolutions.desafioandroid.helpers.NetworkHelper
import com.concretesolutions.desafioandroid.model.PullRequest
import com.concretesolutions.desafioandroid.service.RepositoryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestViewModel(ctx: Context) : BaseViewModel(ctx) {

    private var pullRequests: MutableLiveData<MutableList<PullRequest>> = MutableLiveData()
    private val repositoriesService = NetworkHelper.getRetrofitInstanceGitHub()
        .create(RepositoryService::class.java)

    init {
        pullRequests.value = arrayListOf()
    }

    fun getPullRequests() = pullRequests

    fun loadPulls(owner: String, repo: String) {
        repositoriesService.getRepositoryPulls(owner, repo)
            .enqueue(object : Callback<List<PullRequest>>{

                override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                    status.value = LoadStatus(true, context.getString(R.string.error_load_repos))
                }

                override fun onResponse(
                    call: Call<List<PullRequest>>,
                    response: Response<List<PullRequest>>
                ) {
                    status.value = LoadStatus(true, "")
                    var result = response.body()
                    if (result != null) {
                        pullRequests.value = result.toMutableList()
                    }
                }

            })

    }
}