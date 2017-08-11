package br.com.concrete.sdk

import android.arch.lifecycle.LiveData
import br.com.concrete.sdk.data.remote.GithubApi
import br.com.concrete.sdk.handler.Cache
import br.com.concrete.sdk.handler.ResponseHandler
import br.com.concrete.sdk.model.PullRequest
import br.com.concrete.sdk.model.Repo
import br.com.concrete.sdk.model.DataResult

object PullRequestRepository {

    private val api = GithubApi.instance()

    private val pullRequestCacheMap = HashMap<Long, Cache<List<PullRequest>>>()

    fun list(repo: Repo): LiveData<DataResult<List<PullRequest>>> {
        return object : ResponseHandler<List<PullRequest>>() {

            override fun saveRemoteResult(remoteData: List<PullRequest>) {
                pullRequestCacheMap.put(repo.id, Cache(data = remoteData))
            }

            override fun localData() = pullRequestCacheMap[repo.id]

            override fun remoteData() = api.listPullRequest(creator = repo.owner.login, repo = repo.name)
        }
    }
}