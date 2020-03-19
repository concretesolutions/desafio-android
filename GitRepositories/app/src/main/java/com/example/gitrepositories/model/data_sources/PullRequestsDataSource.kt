package com.example.gitrepositories.model.data_sources

import androidx.paging.PageKeyedDataSource
import com.example.gitrepositories.model.dto.PullRequest
import com.example.gitrepositories.model.services.GitHubService
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestsDataSource(private val initialFetchCompletedCallback: (Boolean) -> Unit, private val errorCallback: () -> Unit,
                             private val repoCreator: String, private val repoName: String) : PageKeyedDataSource<Int, PullRequest>(), KoinComponent {

    private val gitHubService: GitHubService by inject()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, PullRequest>) {
        gitHubService.getPullRequests(repoCreator, repoName).enqueue(object : Callback<List<PullRequest>> {
            override fun onResponse(call: Call<List<PullRequest>>, response: Response<List<PullRequest>>) {
                val list = response.body() ?: listOf()
                callback.onResult(list, null, 2)
                initialFetchCompletedCallback.invoke(list.isEmpty())
            }

            override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                errorCallback.invoke()
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PullRequest>) {
        gitHubService.getPullRequests(repoCreator, repoName).enqueue(object : Callback<List<PullRequest>> {
            override fun onResponse(call: Call<List<PullRequest>>, response: Response<List<PullRequest>>) {
                val list = response.body() ?: listOf()
                callback.onResult(list, params.key + 1)
            }

            override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                errorCallback.invoke()
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PullRequest>) {}
}