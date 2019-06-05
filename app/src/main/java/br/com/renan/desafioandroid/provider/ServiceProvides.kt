package br.com.renan.desafioandroid.provider

import br.com.renan.desafioandroid.model.service.PullRequestService
import br.com.renan.desafioandroid.model.service.RepositoryService

object ServiceProvides {

    private var pullRequestService: PullRequestService? = null
    private var repositoryService: RepositoryService? = null

    fun getPullRequestService() = pullRequestService ?: PullRequestService()
    fun getRepositoryService() = repositoryService ?: RepositoryService()
}