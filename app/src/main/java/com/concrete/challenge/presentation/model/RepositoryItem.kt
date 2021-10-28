package com.concrete.challenge.presentation.model

import com.concrete.challenge.data.UserEntity

data class RepositoryItem (
    val repositoryId: String,
    val repositoryName: String,
    val repositoryDescription: String,
    val repositoryOwner: UserEntity,
    val starsAmount: Int,
    val forksAmount: Int,
    val pullRequestsUrl: String
)