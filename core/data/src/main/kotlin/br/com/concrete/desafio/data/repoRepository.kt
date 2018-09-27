package br.com.concrete.desafio.data

import android.support.annotation.RestrictTo
import br.com.concrete.desafio.data.livedata.ResponseLiveData
import br.com.concrete.desafio.data.model.Page
import br.com.concrete.desafio.data.model.dto.RepoDTO
import br.com.concrete.desafio.data.remote.api

interface RepoRepository {

    fun requestPage(page: Int): ResponseLiveData<Page<RepoDTO>>

    fun list(): ResponseLiveData<Page<RepoDTO>>

}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal class RepoRepositoryImpl : RepoRepository {

    override fun requestPage(page: Int) = api.searchRepositories(page = page, perPage = 10)

    override fun list() = requestPage(0)

}