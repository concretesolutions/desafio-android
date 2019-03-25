package dev.renatoneto.githubrepos.network.github

import dev.renatoneto.githubrepos.model.github.GithubPullRequest
import dev.renatoneto.githubrepos.network.github.data.GithubRepositoriesResponse
import io.reactivex.Observable

interface GithubDataSource {

    fun getRepositoriesList(page: Int): Observable<GithubRepositoriesResponse>

    fun getPullRequestsList(owner: String, repository: String): Observable<ArrayList<GithubPullRequest>>

}