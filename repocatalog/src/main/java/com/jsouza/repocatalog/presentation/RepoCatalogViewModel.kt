package com.jsouza.repocatalog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jsouza.repocatalog.data.repocatalog.remote.response.Repository
import com.jsouza.repocatalog.domain.usecase.FetchReposFromApi
import kotlinx.coroutines.flow.Flow

class RepoCatalogViewModel(
    private val fetchReposFromApi: FetchReposFromApi
) : ViewModel() {

    private var currentSearchResult: Flow<PagingData<Repository>>? = null

    fun searchRepo(): Flow<PagingData<Repository>> {
        val newResult: Flow<PagingData<Repository>> = fetchReposFromApi()
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}
