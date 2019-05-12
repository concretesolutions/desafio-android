package com.gdavidpb.github.data.source.remote

import com.gdavidpb.github.data.repository.GitHubDataStore
import com.gdavidpb.github.domain.model.Pull
import com.gdavidpb.github.domain.model.Repository
import com.gdavidpb.github.utils.await
import com.gdavidpb.github.utils.toPull
import com.gdavidpb.github.utils.toRepositorySearchResult

open class GitHubRemoteDataStore(
    private val gitHubApi: GitHubApi
) : GitHubDataStore {
    override suspend fun getRepositories(page: Int) =
        gitHubApi.getRepositories(page = page).await()!!.toRepositorySearchResult(page)

    override suspend fun getPulls(repository: String) =
        gitHubApi.getPulls(repository).await()!!.map { it.toPull() }

    override suspend fun saveRepositories(repositories: List<Repository>) =
        throw UnsupportedOperationException()

    override suspend fun savePulls(repository: String, pulls: List<Pull>) =
        throw UnsupportedOperationException()
}