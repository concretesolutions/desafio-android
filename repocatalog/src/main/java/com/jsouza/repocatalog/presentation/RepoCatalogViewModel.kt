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

    private var currentSearchResult: Flow<PagingData<RepositoryEntity>>? = null

    fun searchRepo(): Flow<PagingData<RepositoryEntity>> {
        val newResult = fetchReposFromApi()
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}
