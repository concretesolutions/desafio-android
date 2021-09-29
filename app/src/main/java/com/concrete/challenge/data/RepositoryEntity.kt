package com.concrete.challenge.data

// Temporal
data class RepositoryEntity (
        val username: String,
        val userName: String,
        val repositoryName: String,
        val repositoryDescription: String,
        val forksAmount: Int,
        val starsAmount: Int,
        val openPullRequestAmount: Int,
        val closedPullRequestAmount: Int
)