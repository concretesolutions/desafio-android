package com.uharris.desafio_android.data.remote

import com.uharris.desafio_android.data.RepositoriesRemote
import com.uharris.desafio_android.data.base.BaseRepository
import com.uharris.desafio_android.data.base.Failure
import com.uharris.desafio_android.data.base.Result
import com.uharris.desafio_android.data.services.RepositoriesServices
import com.uharris.desafio_android.domain.models.PullRequest
import com.uharris.desafio_android.domain.models.reponse.RepositoryResponse
import com.uharris.desafio_android.presentation.base.NetworkHandler
import javax.inject.Inject

class RepositoriesRemoteImpl @Inject constructor(private val networkHandler: NetworkHandler,
                                                 private val repositoriesServices: RepositoriesServices): BaseRepository(), RepositoriesRemote {
    override suspend fun getRepositories(page: Int): Result<RepositoryResponse> {
        return when(networkHandler.isConnected){
            true -> safeApiCall(repositoriesServices.getRepositories(page), RepositoryResponse())
            false, null -> Result.Error(Failure.NetworkConnection)
        }
    }

    override suspend fun getRepositoriesPullRequests(creator: String, repository: String): Result<List<PullRequest>> {
        return when(networkHandler.isConnected){
            true -> safeApiCall(repositoriesServices.getRepositoryPullRequests(creator, repository), mutableListOf())
            false, null -> Result.Error(Failure.NetworkConnection)
        }
    }

}