package com.concrete.challenge.presentation.model

import com.concrete.challenge.data.UserEntity
import com.google.gson.annotations.SerializedName

data class RepositoryItem (
    val repositoryId: String,
    val repositoryName: String,
    val repositoryDescription: String,
    val repositoryOwner: UserEntity,
    val starsAmount: Int,
    val forksAmount: Int,

    val openPullRequestAmount: Int,
    val closedPullRequestAmount: Int
)