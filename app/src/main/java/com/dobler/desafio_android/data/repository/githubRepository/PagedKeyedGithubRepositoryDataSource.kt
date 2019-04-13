package com.dobler.desafio_android.data.repository.githubRepository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.dobler.desafio_android.data.api.githubRepository.GithubRepositoryResponse
import com.dobler.desafio_android.data.api.githubRepository.GithubRepositoryService
import com.dobler.desafio_android.data.model.GithubRepository
import com.dobler.desafio_android.util.paging.NetworkState
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.Executors

class PageKeyedSubredditDataSource(
    private val api: GithubRepositoryService
) : PageKeyedDataSource<String, GithubRepository>() {

    private val retryExecutor = Executors.newFixedThreadPool(5)

    private var retry: (() -> Any)? = null

    var page = 1;

    /**
     * There is no sync on the state because paging will always call loadInitial first then wait
     * for it to return some success value before calling loadAfter.
     */
    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            retryExecutor.execute {
                it.invoke()
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, GithubRepository>
    ) {
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, GithubRepository>) {
        networkState.postValue(NetworkState.LOADING)

        api.getPage("language:Java", "stars", page++).enqueue(
            object : retrofit2.Callback<GithubRepositoryResponse> {
                override fun onFailure(call: Call<GithubRepositoryResponse>, t: Throwable) {
                    retry = {
                        loadAfter(params, callback)
                    }
                    networkState.postValue(NetworkState.error(t.message ?: "unknown err"))
                }

                override fun onResponse(
                    call: Call<GithubRepositoryResponse>,
                    response: Response<GithubRepositoryResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()?.items
                        val items = data?.map { it } ?: emptyList()
                        retry = null
                        callback.onResult(items, page.toString())
                        networkState.postValue(NetworkState.LOADED)
                    } else {
                        retry = {
                            loadAfter(params, callback)
                        }
                        networkState.postValue(
                            NetworkState.error("error code: ${response.code()}")
                        )
                    }
                }
            }
        )
    }

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, GithubRepository>
    ) {
        val currentPage = page
        Log.e("DataSOurce",params.toString())
        val request = api.getPage("language:Java", "stars", currentPage)
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        // triggered by a refresh, we better execute sync
        try {
            val response = request.execute()
            val data = response.body()?.items
            val items = data?.map { it} ?: emptyList()
            retry = null
            networkState.postValue(NetworkState.LOADED)
            initialLoad.postValue(NetworkState.LOADED)
            callback.onResult(items, page--.toString(), page++.toString())
        } catch (ioException: IOException) {
            retry = {
                loadInitial(params, callback)
            }
            val error = NetworkState.error(ioException.message ?: "unknown error")
            networkState.postValue(error)
            initialLoad.postValue(error)
        }
    }
}