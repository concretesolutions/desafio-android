package com.bassul.mel.app.feature.pullRequestList.repository

import com.bassul.mel.app.callback.RepositorySelectedRepositoriesCallback
import com.bassul.mel.app.endpoint.GithubAPI
import com.bassul.mel.app.feature.pullRequestList.PullRequestListContract
import com.bassul.mel.app.feature.pullRequestList.repository.model.PullRequestListResponse
import retrofit2.Call
import retrofit2.Response

class PullRequestRepositoryImpl(private val githubAPI: GithubAPI) : PullRequestListContract.Repository {

    override fun readPullRequestJson(login: String, repositoryName : String, callback: RepositorySelectedRepositoriesCallback) {
        githubAPI.fetchPullRequestData(login, repositoryName).enqueue(object : retrofit2.Callback<List<PullRequestListResponse>>{
            override fun onResponse(
                call: Call<List<PullRequestListResponse>>,
                response: Response<List<PullRequestListResponse>>
            ) {
                response.body()?.let {
                    callback.onSuccess(it)
                }
            }

            override fun onFailure(call: Call<List<PullRequestListResponse>>, t: Throwable) {
                callback.onError(t.localizedMessage)
            }
        })
    }
}
