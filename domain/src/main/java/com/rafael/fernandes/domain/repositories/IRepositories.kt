package com.rafael.fernandes.domain.repositories

import com.rafael.fernandes.domain.model.*
import io.reactivex.Single

interface IRepositories {
    fun getOnlineRepositories(gitRepositoryRequest: GitRepositoryRequest): Single<GitRepositories>
    fun getPullRequest(gitPullRequestRequest: GitPullRequestRequest): Single<List<GitPullRequests>>
}