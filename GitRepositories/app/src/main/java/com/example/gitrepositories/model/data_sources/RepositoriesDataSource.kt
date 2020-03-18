package com.example.gitrepositories.model.data_sources

import androidx.paging.PageKeyedDataSource
import com.example.gitrepositories.model.dto.Repository
import com.example.gitrepositories.model.services.GitHubService
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoriesDataSource(private val initialFetchCompletedCallback: (Boolean) -> Unit, private val errorCallback: () -> Unit) : PageKeyedDataSource<Int, Repository>(), KoinComponent {

    private val gitHubService: GitHubService by inject()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Repository>) {
        gitHubService.getRepositories(1).enqueue(object : Callback<List<Repository>> {
            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                val list = response.body()!!
                callback.onResult(list, null, 2)
                initialFetchCompletedCallback.invoke(list.isEmpty())
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                errorCallback.invoke()
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {
        gitHubService.getRepositories(params.key).enqueue(object : Callback<List<Repository>> {
            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                callback.onResult(response.body()!!, params.key + 1)
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                errorCallback.invoke()
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {}
}