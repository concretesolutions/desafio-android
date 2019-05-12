package com.gdavidpb.github.domain.repository

import com.gdavidpb.github.domain.model.Pull
import com.gdavidpb.github.domain.model.Repository
import com.gdavidpb.github.domain.model.SearchResult

interface VCSRepository {
    suspend fun getRepositories(page: Int): SearchResult<Repository>
    suspend fun getPulls(repository: String): List<Pull>
}