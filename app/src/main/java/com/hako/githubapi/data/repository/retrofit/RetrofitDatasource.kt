package com.hako.githubapi.data.repository.retrofit

import com.hako.githubapi.domain.entities.PullRequest
import com.hako.githubapi.domain.entities.Repositories
import com.hako.githubapi.domain.entities.Repository
import com.hako.githubapi.domain.requests.QueryPullRequest
import com.hako.githubapi.domain.requests.QueryRepository
import io.reactivex.Observable
import org.koin.standalone.KoinComponent
import org.koin.standalone.get

class RetrofitDatasource : KoinComponent, GithubContract {

    private val api: GithubApi = get()

    override fun getRepositories(query: QueryRepository): Observable<List<Repository>> {
        return api.getTopRepositories(query.language, query.sort, query.page).map { return@map it.items }
    }

    override fun getPullsRequests(queryPullRequest: QueryPullRequest): Observable<List<PullRequest>> {
        return api.getPullRequests(queryPullRequest.author, queryPullRequest.name)
    }
}
