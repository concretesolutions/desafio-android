package com.uharris.desafio_android.domain.usecases.actions

import com.uharris.desafio_android.data.RepositoriesRemote
import com.uharris.desafio_android.data.base.Result
import com.uharris.desafio_android.domain.models.reponse.RepositoryResponse
import com.uharris.desafio_android.domain.usecases.base.UseCase
import javax.inject.Inject

class FetchRepositories @Inject constructor(private val repositoriesRemote: RepositoriesRemote): UseCase<RepositoryResponse, FetchRepositories.Params>() {
    override suspend fun run(params: FetchRepositories.Params): Result<RepositoryResponse> = repositoriesRemote.getRepositories(params.page)

    data class Params(val page: Int)
}