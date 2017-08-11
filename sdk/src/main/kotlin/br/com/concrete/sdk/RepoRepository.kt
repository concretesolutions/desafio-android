package br.com.concrete.sdk

import android.arch.lifecycle.LiveData
import br.com.concrete.sdk.data.remote.GithubApi
import br.com.concrete.sdk.extension.nextPage
import br.com.concrete.sdk.handler.ResponseHandler
import br.com.concrete.sdk.model.DataResult
import br.com.concrete.sdk.model.Page
import br.com.concrete.sdk.model.Repo
import retrofit2.Response

object RepoRepository {

    private val api = GithubApi.instance()

    fun search(page: Int): LiveData<DataResult<Page<Repo>>> {
        return object : ResponseHandler<Page<Repo>>() {

            override fun remoteData() = api.searchRepositories(page = page, perPage = 10)

            override fun extractDataFromResponse(response: Response<Page<Repo>>) =
                    super.extractDataFromResponse(response)?.apply {
                        nextPage = response.nextPage()
                    }
        }
    }
}