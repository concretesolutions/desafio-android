package com.uharris.desafio_android.data

import com.uharris.desafio_android.data.base.Result
import com.uharris.desafio_android.domain.models.PullRequest
import com.uharris.desafio_android.domain.models.reponse.RepositoryResponse

interface RepositoriesRemote {

    suspend fun getRepositories(page: Int): Result<RepositoryResponse>

    suspend fun getRepositoriesPullRequests(creator: String, repository: String): Result<List<PullRequest>>
}