package com.example.desafioconcentresolutions.datasource

import androidx.paging.PageKeyedDataSource
import com.example.desafioconcentresolutions.models.GitHubRepo
import com.example.desafioconcentresolutions.models.GitHubRepoPage
import com.example.desafioconcentresolutions.service.GitHubAPI
import com.example.desafioconcentresolutions.service.GitHubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubRepoDataSource() : PageKeyedDataSource<Int,GitHubRepo>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GitHubRepo>) {

        GitHubAPI.getGitHubApi().listAllRepoByPage(1).enqueue(object: Callback<GitHubRepoPage>{
            override fun onResponse(call: Call<GitHubRepoPage>, response: Response<GitHubRepoPage>) {
                if(response.isSuccessful){
                    val gitRepo = response.body()?.items?.toMutableList() ?: mutableListOf()
                    callback.onResult(gitRepo, null, 2)

                }
            }

            override fun onFailure(call: Call<GitHubRepoPage>, t: Throwable) {
                callback.onResult(mutableListOf(),null, null)
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GitHubRepo>) {
        GitHubAPI.getGitHubApi().listAllRepoByPage(params.key).enqueue(object: Callback<GitHubRepoPage>{
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