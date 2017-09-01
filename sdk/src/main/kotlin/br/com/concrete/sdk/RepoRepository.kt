package br.com.concrete.sdk

import br.com.concrete.sdk.data.remote.GithubApi

object RepoRepository {

    private val api = GithubApi.instance

    fun requestPage(page: Int) = api.searchRepositories(page = page, perPage = 10)

    fun list() = requestPage(0)

}