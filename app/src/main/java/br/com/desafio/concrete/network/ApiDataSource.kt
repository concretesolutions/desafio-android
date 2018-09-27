package br.com.desafio.concrete.network

import br.com.desafio.concrete.model.GitHubResponse
import br.com.desafio.concrete.model.PullRequest
import br.com.desafio.concrete.model.Repository
import io.reactivex.Observable


/**
 * Created by Malkes on 24/09/2018.
 */
interface ApiDataSource {
    fun fetchRepositories(technology: String, page: Int): Observable<GitHubResponse>
    fun fetchPullRequests(repoItem: Repository): Observable<ArrayList<PullRequest>>
}