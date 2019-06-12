package com.abs.javarepos.model.datasource

import androidx.paging.PageKeyedDataSource
import com.abs.javarepos.model.Repo
import com.abs.javarepos.model.githubapi.GithubApi
import com.abs.javarepos.model.githubapi.RepoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoDataSource : PageKeyedDataSource<Int, Repo>() {
    private val firstPage: Int = 1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Repo>) {
        GithubApi.endpoints.getRepos(firstPage).enqueue(object : Callback<RepoResponse> {
            override fun onResponse(call: Call<RepoResponse>, response: Response<RepoResponse>) {
                response.body()?.let {
                    callback.onResult(it.items, null, firstPage.inc())
                }
            }

            override fun onFailure(call: Call<RepoResponse>, t: Throwable) {

            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {
        GithubApi.endpoints.getRepos(params.key).enqueue(object : Callback<RepoResponse> {
            override fun onResponse(call: Call<RepoResponse>, response: Response<RepoResponse>) {
                response.body()?.let {
                    callback.onResult(it.items, params.key.inc())
                }
            }

            override fun onFailure(call: Call<RepoResponse>, t: Throwable) {

            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {}
}