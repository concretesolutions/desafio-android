package com.gdavidpb.github.data.repository

import com.gdavidpb.github.domain.model.Pull
import com.gdavidpb.github.domain.model.Repository
import com.gdavidpb.github.domain.model.SearchResult

interface GitHubDataStore {
    suspend fun getRepositories(page: Int): SearchResult<Repository>
    suspend fun getRepositoriesCount(): Long

    suspend fun getPulls(repository: String): List<Pull>

    suspend fun saveRepositories(repositories: List<Repository>)
    suspend fun savePulls(repository: String, pulls: List<Pull>)
}