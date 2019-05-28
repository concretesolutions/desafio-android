package com.jmc.desafioandroidkotlin.domain.repository

import com.jmc.desafioandroidkotlin.domain.model.PullModel
import com.jmc.desafioandroidkotlin.domain.model.RepositoryModel
import com.jmc.desafioandroidkotlin.domain.model.SearchResultModel


interface GithubRepository {
    suspend fun getRepositories(page: Int): SearchResultModel<RepositoryModel>
    suspend fun getPulls(repository: String): List<PullModel>
}