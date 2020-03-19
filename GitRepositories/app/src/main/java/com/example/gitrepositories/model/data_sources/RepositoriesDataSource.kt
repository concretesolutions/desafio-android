package com.example.gitrepositories.model.data_sources

import androidx.paging.PageKeyedDataSource
import com.example.gitrepositories.model.dto.Repository
import com.example.gitrepositories.model.dto.RepositoryResponse
import com.example.gitrepositories.model.services.GitHubService
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoriesDataSource(private val initialFetchCompletedCallback: (Boolean) -> Unit, private val errorCallback: () -> Unit) : PageKeyedDataSource<Int, Repository>(), KoinComponent {

    private val gitHubService: GitHubService by inject()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Repository>) {
        gitHubService.getRepositories(1).enqueue(object : Callback<RepositoryResponse> {
            override fun onResponse(call: Call<RepositoryResponse>, response: Response<RepositoryResponse>) {
                val list = response.body()?.repositories ?: listOf()
                callback.onResult(list, null, 2)
                initialFetchCompletedCallback.invoke(list.isEmpty())
            }

            override fun onFailure(call: Call<RepositoryResponse>, t: Throwable) {
                errorCallback.invoke()
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {
        gitHubService.getRepositories(params.key).enqueue(object : Callback<RepositoryResponse> {
            override fun onResponse(call: Call<RepositoryResponse>, response: Response<RepositoryResponse>) {
                callback.onResult(response.body()!!.repositories, params.key + 1)
            }

            override fun onFailure(call: Call<RepositoryResponse>, t: Throwable) {
                errorCallback.invoke()
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {}
}