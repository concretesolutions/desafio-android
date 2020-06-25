package com.germanofilho.home.repository

import com.germanofilho.network.feature.repositorylist.model.entity.GitRepositoryResponse

interface HomeRepository {
    suspend fun getRepositoryList(page: Int = 1) : GitRepositoryResponse
}