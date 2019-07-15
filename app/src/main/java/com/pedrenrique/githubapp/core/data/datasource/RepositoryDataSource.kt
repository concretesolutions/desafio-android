package com.pedrenrique.githubapp.core.data.datasource

import com.pedrenrique.githubapp.core.data.PaginatedData
import com.pedrenrique.githubapp.core.data.PullRequest
import com.pedrenrique.githubapp.core.data.Repository
import com.pedrenrique.githubapp.core.exceptions.EmptyResultException
import com.pedrenrique.githubapp.core.exceptions.NoMoreResultException
import com.pedrenrique.githubapp.core.net.services.GithubService

interface RepositoryDataSource {

    suspend fun list(): PaginatedData<Repository>

    suspend fun loadMore(lastPage: Int): PaginatedData<Repository>

    suspend fun listPR(repository: Repository): PaginatedData<PullRequest>

    suspend fun listMorePR(lastPage: Int, repository: Repository): PaginatedData<PullRequest>

    class Impl(private val service: GithubService) : RepositoryDataSource {
        override suspend fun list() =
            loadPage(1)

        override suspend fun loadMore(lastPage: Int) =
            try {
                loadPage(lastPage + 1)
            } catch (e: EmptyResultException) {
                throw NoMoreResultException()
            }

        private suspend fun loadPage(page: Int): PaginatedData<Repository> {
            val response = service.list(
                mapOf(
                    "q" to "language:Java",
                    "sort" to "stars",
                    "page" to page.toString()
                )
            ).await()

            if (response.items.isEmpty())
                throw EmptyResultException()

            return PaginatedData(page, response.items)
        }

        override suspend fun listPR(repository: Repository) =
            loadPRs(repository, 1)

        override suspend fun listMorePR(
            lastPage: Int,
            repository: Repository
        ): PaginatedData<PullRequest> =
            try {
                loadPRs(repository, 1 + lastPage)
            } catch (e: EmptyResultException) {
                throw NoMoreResultException()
            }

        private suspend fun loadPRs(
            repository: Repository,
            page: Int
        ): PaginatedData<PullRequest> {
            val response = service.listPR(
                repository.owner.login,
                repository.name,
                page
            ).await()

            if (response.isEmpty())
                throw EmptyResultException()

            return PaginatedData(page, response)
        }
    }
}