package com.pedrenrique.githubapp.core.data.datasource

import com.pedrenrique.githubapp.core.data.PaginatedData
import com.pedrenrique.githubapp.core.data.Repository
import com.pedrenrique.githubapp.core.net.services.GithubService

interface RepositoryDataSource {

    suspend fun list(): PaginatedData<Repository>

    suspend fun loadMore(lastPage: Int): PaginatedData<Repository>

    class Impl(private val service: GithubService) : RepositoryDataSource {
        override suspend fun list() =
            loadPage(1)

        override suspend fun loadMore(lastPage: Int) =
            loadPage(lastPage + 1)

        private suspend fun loadPage(page: Int): PaginatedData<Repository> {
            val response = service.list(
                mapOf(
                    "q" to "language:Java",
                    "sort" to "stars",
                    "page" to page.toString()
                )
            ).await()

            return PaginatedData(page, response.items)
        }
    }
}