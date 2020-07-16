package com.jsouza.repocatalog.domain.repository

import com.jsouza.repocatalog.data.repocatalog.remote.response.RepositoryList

interface CatalogRepository {

    suspend fun refreshRepos(page: Int): RepositoryList?
}
