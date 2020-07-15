package com.bassul.mel.app.feature.repositoriesList.repository

import com.bassul.mel.app.callback.RepositotyAllRepositoriesCallback
import com.bassul.mel.app.endpoint.GithubAPI
import com.bassul.mel.app.feature.repositoriesList.RepoListContract
import com.bassul.mel.app.feature.repositoriesList.repository.model.RepositoriesListResponse
import retrofit2.Call
import retrofit2.Response

class RepoRepositoryImpl(private val githubAPI: GithubAPI) : RepoListContract.Repository {

    override fun readRepositoryJson(pages: Int, callback: RepositotyAllRepositoriesCallback) {
        githubAPI.fetchRepositoryData(pages)
            .enqueue(object : retrofit2.Callback<RepositoriesListResponse> {
                override fun onResponse(
                    call: Call<RepositoriesListResponse>,
                    response: Response<RepositoriesListResponse>
                ) {
                    response.body()?.let {
                        callback.onSuccess(it)
                    }
                }

                override fun onFailure(call: Call<RepositoriesListResponse>, t: Throwable) {
                    callback.onError(t.localizedMessage)
                }
            })
    }

}
