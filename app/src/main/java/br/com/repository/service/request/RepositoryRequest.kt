package br.com.repository.service.request

import br.com.repository.interfaces.endpoint.RepositoryEndPoint
import br.com.repository.model.Repository
import br.com.repository.service.Api
import io.reactivex.Observable

class RepositoryRequest {

    private val requestRepository: RepositoryEndPoint.RepositoryByPage = Api.retrofit!!.create(
            RepositoryEndPoint.RepositoryByPage::class.java
    )

    fun getRepository(page: Int) = Api.retrofit!!.create(
            RepositoryEndPoint.RepositoryByPage::class.java
    ).callRepositoryByPage(page)
                .flatMap { repoResult ->
                    Observable.fromIterable(repoResult.repositoryList)
                            .flatMap { repository ->
                                Observable.just(repository)
                            }
                }
}