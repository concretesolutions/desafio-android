package com.jsouza.repocatalog.domain.usecase

import androidx.paging.PagingData
import com.jsouza.repocatalog.data.repocatalog.remote.response.Repository
import com.jsouza.repocatalog.domain.repository.RepoRepository
import kotlinx.coroutines.flow.Flow

class FetchReposFromApi(
    private val repoRepository: RepoRepository
) {
    operator fun invoke(): Flow<PagingData<Repository>> = repoRepository.getSearchResultStream()
}
