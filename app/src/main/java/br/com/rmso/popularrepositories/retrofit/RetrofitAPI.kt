package br.com.rmso.popularrepositories.retrofit

import br.com.rmso.popularrepositories.retrofit.services.PullRequestService
import br.com.rmso.popularrepositories.retrofit.services.RepositoryService
import br.com.rmso.popularrepositories.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitAPI {
    private val constants = Constants()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(constants.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val repositoryService: RepositoryService
        get() = this.retrofit.create<RepositoryService>(
            RepositoryService::class.java)


    val pullRequestService: PullRequestService
        get() = this.retrofit.create<PullRequestService>(
            PullRequestService::class.java)
}