package com.example.eloyvitorio.githubjavapop.data.network

import com.example.eloyvitorio.githubjavapop.data.model.PullRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestsAPIImpl(private val createRetrofit: CreateRetrofit) : PullRequestsAPI {
        override fun listPullRequest(ownerLogin: String, repositoryName: String, callback: CallBackPullRequest) {
        val apiService = createRetrofit.getApi()
        apiService.getRepositoryPullRequests(ownerLogin, repositoryName).enqueue(object : Callback<List<PullRequest>> {
            override fun onResponse(call: Call<List<PullRequest>>, response: Response<List<PullRequest>>) {
                if (response.isSuccessful) {
                    val pullRequests = response.body()
                    callback.onSucessGetPullRequest(pullRequests!!)
                }
            }

            override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                callback.onErrorGetPullRequest(
                        "Um erro ocorreu. \nTente carregar novamente.")
            }
        })
    }
}

interface PullRequestsAPI {
    fun listPullRequest(ownerLogin: String, repositoryName: String, callback: CallBackPullRequest)
}

interface CallBackPullRequest {
    fun onSucessGetPullRequest(pullRequest: List<PullRequest>)
    fun onErrorGetPullRequest(message: String)
}