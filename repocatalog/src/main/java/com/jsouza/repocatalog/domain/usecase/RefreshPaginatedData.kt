package com.jsouza.repocatalog.domain.usecase

import androidx.paging.PagingData
import com.jsouza.repocatalog.data.local.entity.RepositoryEntity
import com.jsouza.repocatalog.domain.repository.RepoRepository
import kotlinx.coroutines.flow.Flow

class RefreshPaginatedData(
    private val repoRepository: RepoRepository
) {
    suspend operator fun invoke(): Flow<PagingData<RepositoryEntity>> = repoRepository.getSearchResultStream()
}
