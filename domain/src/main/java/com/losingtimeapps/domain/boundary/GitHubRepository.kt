package com.losingtimeapps.domain.boundary

import com.losingtimeapps.domain.entity.PullRequest
import com.losingtimeapps.domain.entity.Repository
import io.reactivex.Completable
import io.reactivex.Single

interface GitHubRepository {

    fun getGitHubRepositories(language: String, sort: String, page: Int): Single<List<Repository>>

    fun getGitHubPullRequests(ownerName: String, repositoryName: String, page: Int): Single<List<PullRequest>>

}