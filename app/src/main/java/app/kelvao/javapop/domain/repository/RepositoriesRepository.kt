package app.kelvao.javapop.domain.repository

import app.kelvao.javapop.domain.network.RestClient
import app.kelvao.javapop.domain.network.response.RepositoriesResponse
import app.kelvao.javapop.domain.network.response.RepositoryResponse
import io.reactivex.Observable
import io.reactivex.Single

object RepositoriesRepository {
    private val repositoriesService = RestClient.repositoriesService

    fun fetchRepositories(language: String, page: Int, sort: String, limit: Int) =
        repositoriesService.getRepositories(language, page, sort, limit)
}