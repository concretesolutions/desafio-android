package com.bassul.mel.app.feature.pullRequestsList.repository

import com.bassul.mel.app.callback.RepositorySelectedRepositoryCallback
import com.bassul.mel.app.endpoint.GithubAPI
import com.bassul.mel.app.feature.pullRequestsList.PullRequestListContract
import com.bassul.mel.app.feature.pullRequestsList.repository.model.PullRequestListResponse
import retrofit2.Call
import retrofit2.Response

class PullRequestRepositoryImpl(private val githubAPI: GithubAPI) :
    PullRequestListContract.Repository {

    override fun readPullRequestJson(
        login: String,
        repositoryName: String,
        callback: RepositorySelectedRepositoryCallback?
    ) {
        githubAPI.fetchPullRequestData(login, repositoryName)
            .enqueue(object : retrofit2.Callback<List<PullRequestListResponse>> {
                override fun onResponse(
                    call: Call<List<PullRequestListResponse>>,
                    response: Response<List<PullRequestListResponse>>
                ) {
                    response.body()?.let {
                        callback?.onSuccess(it)
                    }
                }

                override fun onFailure(call: Call<List<PullRequestListResponse>>, t: Throwable) {
                    callback?.onError(t.localizedMessage)
                }
            })
    }
}
