package com.jsouza.repocatalog.data.repocatalog.paging.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jsouza.repocatalog.data.repocatalog.paging.local.datasource.RepositoryDataSource
import com.jsouza.repocatalog.data.repocatalog.remote.RepositoryCatalogService
import com.jsouza.repocatalog.data.repocatalog.remote.response.Repository
import com.jsouza.repocatalog.domain.repository.RepoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class RepoRepositoryImpl(
    private val catalogService: RepositoryCatalogService
) : RepoRepository {

    override fun getSearchResultStream(): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { RepositoryDataSource(service = catalogService) }
        ).flow
    }
}
