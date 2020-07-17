package com.jsouza.repocatalog.domain.repository

import androidx.paging.PagingData
import com.jsouza.repocatalog.data.repocatalog.remote.response.Repository
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    fun getSearchResultStream(): Flow<PagingData<Repository>>
}
