package com.bassul.mel.app.endpoint

import com.bassul.mel.app.ext.NetworkUtils
import com.bassul.mel.app.feature.repositoriesList.repository.model.PullRequestListResponse
import com.bassul.mel.app.feature.repositoriesList.repository.model.RepositoriesListResponse
import retrofit2.Call

class GithubAPI {

        private val retrofitClient =
            NetworkUtils.getRetrofitInstance(
                GithubService.BASE_URL)

        private val service = retrofitClient.create(GithubService::class.java)

        fun fetchRepositoryData(pages : Int): Call<RepositoriesListResponse> {
            return service.fetchRepository(pages)
        }

    fun fetchPullRequestData(login: String, repositoryName : String): Call<List<PullRequestListResponse>> {
        return service.fetchPullRequest(login, repositoryName)
    }
}