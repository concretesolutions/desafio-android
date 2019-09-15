package com.desafioandroid.feature.pullrequest.repository

import com.desafioandroid.data.model.pullrequest.entity.PullRequestResponse
import com.desafioandroid.data.source.remote.ApiService

class PullRequestRepository(private val apiService: ApiService) {

    suspend fun getList(userName: String, repositoryName: String) : List<PullRequestResponse>?{
        val pullRequestResponse = apiService.getListPullRequest(userName, repositoryName)
        return pullRequestResponse.toList()
    }
}