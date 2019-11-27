package com.haldny.githubapp.domain.repository

import com.haldny.githubapp.domain.model.Repository

class GithubRepository(private val githubApi: GithubApi): IGithubRepository {

    override suspend fun getRepositories(page: Int): List<Repository>? {
        return githubApi.getRepositories(page)?.items
    }

}

interface IGithubRepository {

    suspend fun getRepositories(page: Int): List<Repository>?

}