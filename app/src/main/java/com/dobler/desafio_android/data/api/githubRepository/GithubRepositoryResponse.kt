package com.dobler.desafio_android.data.api.githubRepository

import com.dobler.desafio_android.data.model.GithubRepository


data class GithubRepositoryResponse(
        val incomplete_results: Boolean,
        val items: List<GithubRepository>,
        val total_count: Int
)