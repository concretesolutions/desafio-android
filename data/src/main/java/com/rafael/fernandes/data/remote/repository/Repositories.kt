package com.rafael.fernandes.data.remote.repository

import com.rafael.fernandes.data.network.RestApi
import com.rafael.fernandes.domain.model.GitPullRequestRequest
import com.rafael.fernandes.domain.model.GitPullRequests
import com.rafael.fernandes.domain.model.GitRepositories
import com.rafael.fernandes.domain.model.GitRepositoryRequest
import com.rafael.fernandes.domain.repositories.IRepositories
import io.reactivex.Single
import javax.inject.Inject

class Repositories @Inject constructor(
    private val restApi: RestApi
) : IRepositories {

    override fun getOnlineRepositories(gitRepositoryRequest: GitRepositoryRequest): Single<GitRepositories> {
        return restApi.getOnlineRepositories("stars", gitRepositoryRequest.pageNumber)
    }

    override fun getPullRequest(gitPullRequestRequest: GitPullRequestRequest): Single<List<GitPullRequests>> {
        return restApi.listPullRequests(gitPullRequestRequest.user, gitPullRequestRequest.repository)
    }
}