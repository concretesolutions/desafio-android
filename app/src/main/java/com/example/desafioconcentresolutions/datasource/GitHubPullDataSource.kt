package com.example.desafioconcentresolutions.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.desafioconcentresolutions.API.GitHubApi
import com.example.desafioconcentresolutions.models.GitHubPull
import com.example.desafioconcentresolutions.models.Operation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubPullDataSource(private val ownerName:String, private val login:String) : PageKeyedDataSource<Int, GitHubPull>(){

    private val operation = MutableLiveData<Operation>()

    fun getOperationStatus() = operation

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GitHubPull>) {
        operation.postValue(Operation.LOADING)
        GitHubApi.getGitHubApi().listAllPRByPage( ownerName, login, 1, params.requestedLoadSize).enqueue(object: Callback<List<GitHubPull>> {
            override fun onResponse(call: Call<List<GitHubPull>>, response: Response<List<GitHubPull>>) {
                if(response.isSuccessful){
                    val gitRepo = response.body()?.toMutableList() ?: mutableListOf()
                    callback.onResult(gitRepo, null, 2)
                    operation.postValue(Operation.SUCESS)
                }
            }

            override fun onFailure(call: Call<List<GitHubPull>>, t: Throwable) {
                callback.onResult(mutableListOf(),null,null)
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GitHubPull>) {
        GitHubApi.getGitHubApi().listAllPRByPage( ownerName, login, params.key, params.requestedLoadSize).enqueue(object: Callback<List<GitHubPull>> {
            override fun onResponse(call: Call<List<GitHubPull>>, response: Response<List<GitHubPull>>) {
                if(response.isSuccessful){
                    val gitRepo = response.body()?.toMutableList() ?: mutableListOf()
                    callback.onResult(gitRepo, params.key + 1)
                }
            }

            override fun onFailure(call: Call<List<GitHubPull>>, t: Throwable) {
                callback.onResult(mutableListOf(),null)
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GitHubPull>) {

    }
}