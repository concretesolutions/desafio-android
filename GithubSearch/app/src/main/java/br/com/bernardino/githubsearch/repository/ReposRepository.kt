package br.com.bernardino.githubsearch.repository

import br.com.bernardino.githubsearch.model.PullRequest
import org.koin.core.KoinComponent

interface ReposRepository {
    suspend fun refreshRepositories(page: Int)
    suspend fun getPullRequest(creator: String, repository: String): List<PullRequest>
}