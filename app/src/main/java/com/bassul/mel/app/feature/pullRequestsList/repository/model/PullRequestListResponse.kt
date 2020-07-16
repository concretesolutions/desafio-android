package com.bassul.mel.app.feature.pullRequestsList.repository.model

data class PullRequestListResponse(
    val html_url: String,
    val updated_at: String,
    val body: String,
    val user: UserResponse
)

data class UserResponse(
    val login: String,
    val avatar_url: String
)

