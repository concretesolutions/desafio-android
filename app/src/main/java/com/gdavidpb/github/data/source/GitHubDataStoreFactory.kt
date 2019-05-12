package com.gdavidpb.github.data.source

import com.gdavidpb.github.data.repository.GitHubDataStore
import com.gdavidpb.github.data.source.local.GitHubCacheDataStore
import com.gdavidpb.github.data.source.remote.GitHubRemoteDataStore

open class GitHubDataStoreFactory(
    private val gitHubCacheDataStore: GitHubCacheDataStore,
    private val gitHubRemoteDataStore: GitHubRemoteDataStore
) {
    fun retrieveCacheDataStore(): GitHubDataStore = gitHubCacheDataStore
    fun retrieveRemoteDataStore(): GitHubDataStore = gitHubRemoteDataStore
}