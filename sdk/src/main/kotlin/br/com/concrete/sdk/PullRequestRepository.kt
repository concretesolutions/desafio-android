package br.com.concrete.sdk

import android.arch.lifecycle.LiveData
import br.com.concrete.sdk.data.remote.GithubApi
import br.com.concrete.sdk.handler.Cache
import br.com.concrete.sdk.handler.Response
import br.com.concrete.sdk.handler.ResponseHandler
import br.com.concrete.sdk.model.PullRequest
import br.com.concrete.sdk.model.Repo
import retrofit2.Call

object PullRequestRepository {

    private val api = GithubApi.instance()

    private val repoCacheMap = HashMap<String, Cache<List<PullRequest>>>()

    fun list(repo: Repo): LiveData<Response<List<PullRequest>>> {
        return object : ResponseHandler<List<PullRequest>>(repoCacheMap) {
            override fun requestFromServer(): Call<List<PullRequest>> = api.listPullRequest(creator = repo.owner.login, repo = repo.name)

            override fun buildCacheKey() = "${repo.owner.login}|${repo.name}"

        }
    }

}