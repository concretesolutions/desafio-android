package br.com.concrete.sdk

import br.com.concrete.sdk.data.remote.GithubApi
import br.com.concrete.sdk.model.Repo

object PullRequestRepository {

    private val api = GithubApi.instance

    fun list(repo: Repo) =
            api.listPullRequest(creator = repo.owner.login, repo = repo.name)
}