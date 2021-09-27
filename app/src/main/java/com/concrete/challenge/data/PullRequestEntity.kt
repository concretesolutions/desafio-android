package com.concrete.challenge.data

// Temporal
data class PullRequestEntity(
    val openPullRequestAmount: Int,
    val closedPullRequestAmount: Int,
    val pullRequestTitle: String,
    val pullRequestBody: String,
    val username: String,
    val userName: String
)
