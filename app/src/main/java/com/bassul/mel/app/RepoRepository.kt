package com.bassul.mel.app

import android.util.Log
import retrofit2.Call
import retrofit2.Response

class RepoRepository(private val githubAPI: GithubAPI){

    fun readRepositoryJson(callback: RepositotyAllRepositoriesCallback){
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