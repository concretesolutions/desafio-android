package com.concrete.desafio_android.data.external

import com.concrete.desafio_android.BuildConfig.BASE_URL
import com.concrete.desafio_android.R
import com.concrete.desafio_android.contract.PullRequestsContract
import com.concrete.desafio_android.contract.RepositoriesContract
import com.concrete.desafio_android.domain.PullRequest
import com.concrete.desafio_android.domain.Repository
import com.concrete.desafio_android.domain.RepositoryResponse
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubRepository(
    private val repositoriesCallback: RepositoriesContract.Callback? = null,
    private val pullRequestsCallback: PullRequestsContract.Callback? = null
) : RepositoriesContract.Api,
    PullRequestsContract.Api {

    private val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
    private val JAVA_LANGUAGE = "language:Java"
    private val SORT_BY_STARS = "stars"

    val gson = GsonBuilder()
        .setDateFormat(DATE_FORMAT)
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(GithubService::class.java)

    override fun listJavaRepositories(pageNumber: Int) {
        retrofit.listJavaRepositories(JAVA_LANGUAGE, SORT_BY_STARS, pageNumber)
            .enqueue(object : Callback<RepositoryResponse> {
                override fun onResponse(
                    call: Call<RepositoryResponse>,
                    response: Response<RepositoryResponse>
                ) {
                    if (response.isSuccessful) {
                        val repositoryResponse = response.body() as RepositoryResponse
                        repositoriesCallback?.onSuccess(repositoryResponse.items as ArrayList<Repository>)
                    } else {
                        repositoriesCallback?.onError(R.string.unknown_error)
                    }
                }

                override fun onFailure(call: Call<RepositoryResponse>, t: Throwable) {
                    t.printStackTrace()
                    repositoriesCallback?.onError(R.string.connection_problem)
                }

            })
    }

    override fun listPullRequests(creator: String, repository: String) {
        retrofit.listPullRequests(creator, repository)
            .enqueue(object : Callback<List<PullRequest>> {
                override fun onResponse(
                    call: Call<List<PullRequest>>,
                    response: Response<List<PullRequest>>
                ) {
                    if (response.isSuccessful) {
                        pullRequestsCallback?.onSuccess(response.body() as ArrayList<PullRequest>)
                    } else {
                        pullRequestsCallback?.onError(R.string.unknown_error)
                    }
                }

                override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                    t.printStackTrace()
                    pullRequestsCallback?.onError(R.string.connection_problem)
                }
            })

    }


}