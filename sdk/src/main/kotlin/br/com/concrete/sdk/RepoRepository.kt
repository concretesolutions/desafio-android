package br.com.concrete.sdk

import br.com.concrete.sdk.data.remote.GithubApi
import br.com.concrete.sdk.extension.extractPage
import br.com.concrete.sdk.model.Page
import br.com.concrete.sdk.model.Repo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread

object RepoRepository {

    private val api = GithubApi.instance()

    fun search(page: Int): Observable<Page<Repo>> {
        return api.searchRepositories(page = page, perPage = 1)
                .map({
                    it?.body()!!.apply {
                        nextPage = it.headers().get("Link").extractPage("next")
                    }
                })
                .observeOn(mainThread())
    }
}