package com.losingtimeapps.data.remote

import com.losingtimeapps.data.mapper.ResponseMapper
import com.losingtimeapps.domain.boundary.BaseScheduler
import com.losingtimeapps.domain.boundary.GitHubRepository
import com.losingtimeapps.domain.entity.PullRequest
import com.losingtimeapps.domain.entity.Repository
import io.reactivex.Single
import io.reactivex.exceptions.Exceptions


class GitHubRepositoryImp(
    private val gitHubService: GitHubService,
    private val responseMapper: ResponseMapper

) : GitHubRepository {


    override fun getGitHubRepositories(language: String, sort: String, page: Int): Single<List<Repository>> {

        return gitHubService.getGitHubRepository(language, sort, page).map { input ->
            try {
                responseMapper.apply(input)
            } catch (t: Throwable) {
                throw Exceptions.propagate(t)
            }
        }
    }

    override fun getGitHubPullRequests(
        ownerName: String,
        repositoryName: String,
        page: Int
    ): Single<List<PullRequest>> {
        return gitHubService.getGitHubPullRequest(ownerName, repositoryName, page,"all").map { input ->
            try {
                responseMapper.apply(input)
            } catch (t: Throwable) {
                throw Exceptions.propagate(t)
            }


        }
    }
}