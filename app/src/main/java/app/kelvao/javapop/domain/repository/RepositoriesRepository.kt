package app.kelvao.javapop.domain.repository

import app.kelvao.javapop.domain.network.RestClient
import app.kelvao.javapop.domain.network.response.RepositoryResponse
import io.reactivex.Observable

object RepositoriesRepository {
    private val repositoriesService = RestClient.repositoriesService
    private val userService = RestClient.userService

    fun fetchRepositories(language: String, page: Int, sort: String, limit: Int): Observable<RepositoryResponse> =
        repositoriesService.getRepositories(language, page, sort, limit)
            .flatMapIterable { it.items }
            .doOnNext {
                userService.getUser(it.owner.login)
            }
}