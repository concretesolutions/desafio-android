package br.com.concrete.sdk

import android.arch.lifecycle.LiveData
import br.com.concrete.sdk.data.remote.GithubApi
import br.com.concrete.sdk.extension.withCache
import br.com.concrete.sdk.handler.Cache
import br.com.concrete.sdk.model.DataResult
import br.com.concrete.sdk.model.PullRequest
import br.com.concrete.sdk.model.Repo

object PullRequestRepository {

    private val api = GithubApi.instance()

    private val pullRequestCacheMap = HashMap<Long, Cache<List<PullRequest>>>()

    fun list(repo: Repo): LiveData<DataResult<List<PullRequest>>> =
            api.listPullRequest(creator = repo.owner.login, repo = repo.name).withCache(
                    localData = { pullRequestCacheMap[repo.id] },
                    saveData = { pullRequestCacheMap.put(repo.id, Cache(data = it)) })
}