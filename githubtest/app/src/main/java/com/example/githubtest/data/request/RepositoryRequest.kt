package com.example.githubtest.data.request

import com.example.githubtest.data.model.PullRequest
import com.example.githubtest.data.model.RepositoryResponse
import com.example.githubtest.data.service.GitHubService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class RepositoryRequest (private val gitHubService: GitHubService) : RepositoryContract{
    override fun getRepositories( language: String, sort: String, page: Int): Observable<RepositoryResponse> {
        return gitHubService
            .getRepositories(language, sort, page)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }

    override fun getPullRequests(owner: String, repository: String, status: String): Observable<ArrayList<PullRequest>> {
        return gitHubService
            .getPullRequests(owner, repository, status)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())

    }

}