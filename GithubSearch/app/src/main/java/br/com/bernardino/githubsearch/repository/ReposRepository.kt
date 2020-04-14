package br.com.bernardino.githubsearch.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import br.com.bernardino.githubsearch.database.RepoSearchResult
import br.com.bernardino.githubsearch.database.RepositoryDatabase
import br.com.bernardino.githubsearch.model.PullRequest
import org.koin.core.KoinComponent

interface ReposRepository {
    fun refreshRepositories() : RepoSearchResult
    suspend fun getPullRequest(creator: String, repository: String): List<PullRequest>
}