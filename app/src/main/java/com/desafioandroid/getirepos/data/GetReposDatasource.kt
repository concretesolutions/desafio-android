package com.desafioandroid.getirepos.data

import com.desafioandroid.getirepos.data.dto.PullsResponse
import com.desafioandroid.getirepos.data.dto.RepositoryError
import com.desafioandroid.getirepos.data.dto.SearchResponse
import com.desafioandroid.getirepos.data.utils.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetReposDatasource {
    fun getRepos(page: Int, listener: SearchResponseListener) {
        val service = RetrofitService.instance.create(GetReposService::class.java).getRepos(page)
        service.enqueue(object: Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                val responseResult = response.body()
                if (response.isSuccessful && null != responseResult) {
                    listener.onSearchResponse(
                        SearchResponse(
                            totalResponse = responseResult.totalResponse,
                            incompleteResults = responseResult.incompleteResults,
                            items = responseResult.items
                        )
                    )
                } else {
                    listener.onError(
                        RepositoryError(
                            message = "Problems reaching destination",
                            errors = null
                        )
                    )
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                listener.onError(
                    RepositoryError(
                        message = t.message ?: "Unexpected error",
                        errors = null
                    )
                )
            }

        })
    }

    fun getPulls(owner: String, repository: String, listener: PullsResponseListener) {
        val service = RetrofitService.instance.create(GetReposService::class.java).getPulls(owner, repository)

        service.enqueue(object: Callback<List<PullsResponse>> {
            override fun onResponse(call: Call<List<PullsResponse>>, response: Response<List<PullsResponse>>) {
                val responseResult = response.body()
                if (response.isSuccessful && null != responseResult) {
                    listener.onPullsResponse(
                        responseResult
                    )
                } else {
                    listener.onError(
                        RepositoryError(
                            message = "Problems reaching destination",
                            errors = null
                        )
                    )
                }
            }

            override fun onFailure(call: Call<List<PullsResponse>>, t: Throwable) {
                listener.onError(
                    RepositoryError(
                        message = t.message ?: "Unexpected error",
                        errors = null
                    )
                )
            }

        })
    }
}
