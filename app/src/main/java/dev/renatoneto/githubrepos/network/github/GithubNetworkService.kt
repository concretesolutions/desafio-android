package dev.renatoneto.githubrepos.network.github

import dev.renatoneto.githubrepos.model.github.GithubPullRequest
import dev.renatoneto.githubrepos.network.github.data.GithubRepositoriesResponse
import io.reactivex.Observable

class GithubNetworkService(private val api: GithubApi) : GithubDataSource {

    override fun getRepositoriesList(page: Int): Observable<GithubRepositoriesResponse> {
        return api.getRepositoriesList("language:Java", "stars", page)
    }

    override fun getPullRequestsList(owner: String, repository: String): Observable<ArrayList<GithubPullRequest>> {
        return api.getPullRequestsList(owner, repository)
    }
}