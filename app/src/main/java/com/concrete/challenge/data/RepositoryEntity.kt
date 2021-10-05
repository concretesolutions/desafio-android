package com.concrete.challenge.data

import com.google.gson.annotations.SerializedName

data class RepositoryEntity (
        @SerializedName("id") val repositoryId: String,
        @SerializedName("name") val repositoryName: String,
        @SerializedName("description") val repositoryDescription: String,
        @SerializedName("owner") val repositoryOwner: UserEntity,
        @SerializedName("stargazers_count") val starsAmount: Int,
        @SerializedName("forks_count") val forksAmount: Int,

        // TODO: Get open and closed pull requests
        @SerializedName("open") val openPullRequestAmount: Int,
        @SerializedName("close") val closedPullRequestAmount: Int
)