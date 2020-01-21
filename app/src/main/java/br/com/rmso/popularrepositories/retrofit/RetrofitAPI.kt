package br.com.rmso.popularrepositories.retrofit

import br.com.rmso.popularrepositories.retrofit.services.PullRequestService
import br.com.rmso.popularrepositories.retrofit.services.RepositoryService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitAPI {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val repositoryService: RepositoryService
        get() = this.retrofit.create<RepositoryService>(
            RepositoryService::class.java)


    val pullRequestService: PullRequestService
        get() = this.retrofit.create<PullRequestService>(
            PullRequestService::class.java)
}