package com.jmc.desafioandroidkotlin.data.dataSource.factory

import com.jmc.desafioandroidkotlin.data.dataSource.GitHubDataStore
import com.jmc.desafioandroidkotlin.data.dataSource.local.GitHubCacheDataStore
import com.jmc.desafioandroidkotlin.data.dataSource.remote.GitHubRemoteDataStore


open class GitHubDataStoreFactory(
    private val gitHubCacheDataStore: GitHubCacheDataStore,
    private val gitHubRemoteDataStore: GitHubRemoteDataStore
) {
    fun retrieveCacheDataStore(): GitHubDataStore = gitHubCacheDataStore
    fun retrieveRemoteDataStore(): GitHubDataStore = gitHubRemoteDataStore
}