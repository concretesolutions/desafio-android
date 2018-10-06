package com.concrete.andresdavid.desafioandroid.repository

import com.concrete.andresdavid.desafioandroid.model.PullRequest
import com.concrete.andresdavid.desafioandroid.remote.GithubApiService
import io.reactivex.Observable

class PullRequestRepository {
    private val api: GithubApiService = GithubApiService.create()

    fun getPullRequestsByRepository(fullName: String): Observable<List<PullRequest>> {
        return api.repoPullRequest(fullName)
    }
}