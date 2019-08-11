package com.example.desafioconcentresolutions.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.desafioconcentresolutions.API.GitHubApi
import com.example.desafioconcentresolutions.models.GitHubRepo
import com.example.desafioconcentresolutions.models.GitHubRepoPage
import com.example.desafioconcentresolutions.models.Operation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubRepoDataSource() : PageKeyedDataSource<Int,GitHubRepo>() {
    private  val operation = MutableLiveData<Operation>()

    fun getOperationStatus() = operation

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GitHubRepo>) {
        operation.postValue(Operation.LOADING)
        GitHubApi.getGitHubApi().listAllRepoByPage(1, params.requestedLoadSize).enqueue(object: Callback<GitHubRepoPage>{
            override fun onResponse(call: Call<GitHubRepoPage>, response: Response<GitHubRepoPage>) {
                if(response.isSuccessful){
                    val gitRepo = response.body()?.items?.toMutableList() ?: mutableListOf()
                    callback.onResult(gitRepo, null, 2)
                    operation.postValue(Operation.SUCESS)
                }
            }

            override fun onFailure(call: Call<GitHubRepoPage>, t: Throwable) {
                callback.onResult(mutableListOf(),null, null)
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GitHubRepo>) {
        GitHubApi.getGitHubApi().listAllRepoByPage(params.key, params.requestedLoadSize).enqueue(object: Callback<GitHubRepoPage>{
            override fun onResponse(call: Call<GitHubRepoPage>, response: Response<GitHubRepoPage>) {
                if(response.isSuccessful){
                    val gitRepo = response.body()?.items?.toMutableList() ?: mutableListOf()
                    callback.onResult(gitRepo, params.key + 1)
                }
            }

            override fun onFailure(call: Call<GitHubRepoPage>, t: Throwable) {
                callback.onResult(mutableListOf(),null)
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GitHubRepo>) {
    }

}