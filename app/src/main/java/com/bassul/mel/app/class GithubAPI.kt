package com.bassul.mel.app

import retrofit2.Call

class GithubAPI {

    private val retrofitClient =
        NetworkUtils.getRetrofitInstance(
            GithubService.BASE_URL)

    private val service = retrofitClient.create(GithubService::class.java)

    fun fetchRepositoryData(): Call<RepositoriesListResponse> {
        return service.fetchRepository(1) //TODO: Implementar passagem do page
    }
}