package com.rafaelmfer.consultinggithub.mvvm.model

import com.rafaelmfer.consultinggithub.mvvm.model.pullrequests.GitPullRequestResponse
import com.rafaelmfer.consultinggithub.mvvm.model.repositories.GitRepositoriesResponse
import com.rafaelmfer.consultinggithub.retrofit.RetrofitService
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

        serviceApi.getPullRequests(creator, repository, page).enqueue(object : Callback<List<GitPullRequestResponse>> {

            override fun onResponse(call: Call<List<GitPullRequestResponse>>, response: Response<List<GitPullRequestResponse>>) {
                response.body()?.let {
                    listener.onSuccess(it)
                } ?: listener.onError(Exception())
            }

            override fun onFailure(call: Call<List<GitPullRequestResponse>>, t: Throwable) {
                listener.onError(t)
            }
        })
    }
}