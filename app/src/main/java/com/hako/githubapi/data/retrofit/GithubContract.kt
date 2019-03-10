package com.hako.githubapi.data.retrofit

import com.hako.githubapi.domain.entities.PullRequest
import com.hako.githubapi.domain.entities.Repository
import com.hako.githubapi.domain.requests.QueryPullRequest
import com.hako.githubapi.domain.requests.QueryRepository
import io.reactivex.Observable

interface GithubContract {
    fun getRepositories(query: QueryRepository): Observable<List<Repository>>
    fun getPullsRequests(queryPullRequest: QueryPullRequest): Observable<List<PullRequest>>
}
