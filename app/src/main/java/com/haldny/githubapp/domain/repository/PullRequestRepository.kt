package com.haldny.githubapp.domain.repository

import com.haldny.githubapp.domain.model.ResponsePullRequest

class PullRequestRepository(private val githubApi: GithubApi): IPullRequestRepository {

    override suspend fun getPullRequests(owner: String, repository: String): List<ResponsePullRequest>?{
        return githubApi.getPullRequests(owner, repository)
    }

}

interface IPullRequestRepository {

    suspend fun getPullRequests(owner: String, repository: String): List<ResponsePullRequest>?

}