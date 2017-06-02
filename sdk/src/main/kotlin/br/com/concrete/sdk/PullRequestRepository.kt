package br.com.concrete.sdk

import br.com.concrete.sdk.data.remote.GithubApi
import br.com.concrete.sdk.model.PullRequest
import br.com.concrete.sdk.model.Repo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread

object PullRequestRepository {

    private val api = GithubApi.instance()

    fun list(repo: Repo): Observable<List<PullRequest>> = api.listPullRequest(creator = repo.owner.login, repo = repo.name).observeOn(mainThread())
}