package com.abs.javarepos.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abs.javarepos.model.PullRequest
import com.abs.javarepos.model.Repo
import com.abs.javarepos.model.githubapi.GithubApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestRepository {

    private val pullRequests = MutableLiveData<ArrayList<PullRequest>>()

    fun getPullRequests(repo: Repo): LiveData<ArrayList<PullRequest>> {
        GithubApi.endpoints.getPullRequests(repo.owner.login, repo.name).enqueue(object : Callback<List<PullRequest>> {
            override fun onResponse(call: Call<List<PullRequest>>, response: Response<List<PullRequest>>) {
                response.body()?.let {
                    pullRequests.postValue(ArrayList(it))
                }
            }

            override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {

            }
        })
        return pullRequests
    }
}