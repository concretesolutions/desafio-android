package br.com.concrete.sdk

import android.arch.lifecycle.LiveData
import br.com.concrete.sdk.data.remote.GithubApi
import br.com.concrete.sdk.extension.extractPage
import br.com.concrete.sdk.handler.Response
import br.com.concrete.sdk.handler.ResponseHandler
import br.com.concrete.sdk.model.Page
import br.com.concrete.sdk.model.Repo

object RepoRepository {

    private val api = GithubApi.instance()

    fun search(page: Int): LiveData<Response<Page<Repo>>> {
        return object : ResponseHandler<Page<Repo>>() {
            override fun requestFromServer() = api.searchRepositories(page = page, perPage = 10)

            override fun buildCacheKey() = ""

            override fun extractDataFromRemoteResponse(response: retrofit2.Response<Page<Repo>>): Page<Repo>? {
                val data = super.extractDataFromRemoteResponse(response)
                data?.nextPage = response.headers().get("Link").extractPage("next")
                return data
            }

            override fun saveRemoteData(key: String, remoteData: Page<Repo>, cachedData: Page<Repo>?) {}
        }
    }
}