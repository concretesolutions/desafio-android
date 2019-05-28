package com.jmc.desafioandroidkotlin.data.dataSource

import com.jmc.desafioandroidkotlin.domain.model.PullModel
import com.jmc.desafioandroidkotlin.domain.model.RepositoryModel
import com.jmc.desafioandroidkotlin.domain.model.SearchResultModel


interface GitHubDataStore {
    suspend fun getRepositories(page: Int): SearchResultModel<RepositoryModel>
    suspend fun getPulls(repository: String): List<PullModel>

    suspend fun saveRepositories(repositoryModels: List<RepositoryModel>)
    suspend fun savePulls(repository: String, pullModels: List<PullModel>)
}