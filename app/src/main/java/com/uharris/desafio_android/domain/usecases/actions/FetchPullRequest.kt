package com.uharris.desafio_android.domain.usecases.actions

import com.uharris.desafio_android.data.RepositoriesRemote
import com.uharris.desafio_android.data.base.Result
import com.uharris.desafio_android.domain.models.PullRequest
import com.uharris.desafio_android.domain.usecases.base.UseCase
import javax.inject.Inject

class FetchPullRequest @Inject constructor(private val repositoriesRemote: RepositoriesRemote): UseCase<List<PullRequest>, FetchPullRequest.Params>(){
    override suspend fun run(params: FetchPullRequest.Params): Result<List<PullRequest>> = repositoriesRemote.getRepositoriesPullRequests(params.creator, params.repository)

    data class Params(val creator: String, val repository: String)
}