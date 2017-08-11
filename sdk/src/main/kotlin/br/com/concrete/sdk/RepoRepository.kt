package br.com.concrete.sdk

import android.arch.lifecycle.LiveData
import br.com.concrete.sdk.data.remote.GithubApi
import br.com.concrete.sdk.extension.withoutCache
import br.com.concrete.sdk.model.DataResult
import br.com.concrete.sdk.model.Page
import br.com.concrete.sdk.model.Repo

object RepoRepository {

    private val api = GithubApi.instance()

    fun search(page: Int): LiveData<DataResult<Page<Repo>>> = api.searchRepositories(page = page, perPage = 10).withoutCache()
}