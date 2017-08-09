package br.com.concrete.sdk

import br.com.concrete.sdk.data.remote.GithubApi
import br.com.concrete.sdk.extension.extractPage
import br.com.concrete.sdk.model.Page
import br.com.concrete.sdk.model.Repo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RepoRepository {

    private val api = GithubApi.instance()

    fun search(page: Int): Observable<Page<Repo>> {
        return Observable.create<Response<Page<Repo>>> {
            api.searchRepositories(page = page, perPage = 10).enqueue(object : Callback<Page<Repo>> {
                override fun onFailure(call: Call<Page<Repo>>, e: Throwable) {
                    it.onError(e)
                }

                override fun onResponse(call: Call<Page<Repo>>, response: retrofit2.Response<Page<Repo>>) {
                    if (response.isSuccessful) {
                        it.onNext(response)
                        it.onComplete()
                    }
                }
            })
        }.map({
            it?.body()!!.apply {
                nextPage = it.headers().get("Link").extractPage("next")
            }
        }).observeOn(mainThread())
    }
}