package com.marraps.mvvmshow.numberlist.model

import com.marraps.mvvmshow.retrofit.RetrofitService
import com.rafaelmfer.consultinggithub.model.pullrequests.GitPullRequestResponse
import com.rafaelmfer.consultinggithub.model.repositories.GitRepositoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubRepository {

    private val serviceApi = RetrofitService.getService()

    fun getRepositoriesList(listener: GitHubServiceListener, page: Int) {

        serviceApi.getRepositoriesList(page).enqueue(object : Callback<GitRepositoriesResponse> {

            override fun onResponse(call: Call<GitRepositoriesResponse>, response: Response<GitRepositoriesResponse>) {
                response.body()?.let {
                    listener.onSuccess(it)
                } ?: listener.onError(Exception())
            }

            override fun onFailure(call: Call<GitRepositoriesResponse>, t: Throwable) {
                listener.onError(t)
            }
        })
    }

    fun getPullRequestsList(listener: GitHubServiceListener, creator: String, repository: String, page: Int) {

        serviceApi.getPullRequests(creator, repository, page).enqueue(object : Callback<GitPullRequestResponse> {

            override fun onResponse(call: Call<GitPullRequestResponse>, response: Response<GitPullRequestResponse>) {
                response.body()?.let {
                    listener.onSuccess(it)
                } ?: listener.onError(Exception())
            }

            override fun onFailure(call: Call<GitPullRequestResponse>, t: Throwable) {
                listener.onError(t)
            }
        })
    }
}