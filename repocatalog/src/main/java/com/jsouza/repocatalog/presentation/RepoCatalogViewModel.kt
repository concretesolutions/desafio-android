package com.jsouza.repocatalog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jsouza.repocatalog.data.local.entity.RepositoryEntity
import com.jsouza.repocatalog.domain.usecase.RefreshPaginatedData
import kotlinx.coroutines.flow.Flow

class RepoCatalogViewModel(
    private val fetchReposFromApi: RefreshPaginatedData
) : ViewModel() {

    suspend fun searchRepo(): Flow<PagingData<RepositoryEntity>> {
        return fetchReposFromApi()
            .cachedIn(viewModelScope)
    }
}
