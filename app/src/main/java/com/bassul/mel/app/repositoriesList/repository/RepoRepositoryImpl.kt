package com.bassul.mel.app.repositoriesList.repository

import android.util.Log
import com.bassul.mel.app.repositoriesList.RepositoriesListContract
import com.bassul.mel.app.repositoriesList.repository.model.RepositoriesListResponse
import com.bassul.mel.app.callback.RepositotyAllRepositoriesCallback
import com.bassul.mel.app.endpoint.GithubAPI
import retrofit2.Call
import retrofit2.Response

class RepoRepositoryImpl(private val githubAPI: GithubAPI) : RepositoriesListContract.Repository {

    override fun readRepositoryJson(callback: RepositotyAllRepositoriesCallback){
        githubAPI.fetchRepositoryData().enqueue(object : retrofit2.Callback<RepositoriesListResponse>{
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
}