package br.com.concrete.sdk

import br.com.concrete.sdk.data.remote.GithubApi
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread

object RepoRepository {

    private val api = GithubApi.instance()

    fun search(page: Int) = api.searchRepositories(page = page, perPage = 1).observeOn(mainThread())
}