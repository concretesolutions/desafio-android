package br.com.concrete.sdk

import br.com.concrete.sdk.data.remote.GithubApi
import br.com.concrete.sdk.model.Repo
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread

object PullRequestRepository {

    private val api = GithubApi.instance()

    fun list(repo: Repo) = api.listPullRequest(fullName = repo.fullName)
            .observeOn(mainThread())
}