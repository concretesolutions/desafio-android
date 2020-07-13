package com.bassul.mel.app.repositoriesList.repository

import android.util.Log
import com.bassul.mel.app.repositoriesList.RepositoriesListContract
import com.bassul.mel.app.repositoriesList.repository.model.RepositoriesListResponse
import com.bassul.mel.app.callback.RepositotyAllRepositoriesCallback
import com.bassul.mel.app.callback.RepositotySelectedRepositoriesCallback
import com.bassul.mel.app.endpoint.GithubAPI
import com.bassul.mel.app.repositoriesList.repository.model.PullRequestListResponse
import retrofit2.Call
import retrofit2.Response

class RepoRepositoryImpl(private val githubAPI: GithubAPI) : RepositoriesListContract.Repository {

    override fun readRepositoryJson(pages : Int, callback: RepositotyAllRepositoriesCallback){
        githubAPI.fetchRepositoryData(pages).enqueue(object : retrofit2.Callback<RepositoriesListResponse>{
            override fun onResponse(
                call: Call<RepositoriesListResponse>,
                response: Response<RepositoriesListResponse>
            ) {
                response.body()?.let {
                    callback.onSuccess(it)
                }
            }

            override fun onFailure(call: Call<RepositoriesListResponse>, t: Throwable) {
              //TODO: Implementar onFailure
               Log.i("desafio - android", "erro "+t)
            }

        })
    }

    override fun readPullRequestJson(login: String, repositoryName : String, callback: RepositotySelectedRepositoriesCallback) {
        githubAPI.fetchPullRequestData(login, repositoryName).enqueue(object : retrofit2.Callback<List<PullRequestListResponse>>{
            override fun onResponse(
                call: Call<List<PullRequestListResponse>>,
                response: Response<List<PullRequestListResponse>>
            ) {

            }

            override fun onFailure(call: Call<List<PullRequestListResponse>>, t: Throwable) {

            }


        })
    }
}
