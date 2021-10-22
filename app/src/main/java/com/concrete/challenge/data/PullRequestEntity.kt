package com.concrete.challenge.data

import com.google.gson.annotations.SerializedName

data class PullRequestEntity(
    @SerializedName("html_url") val pullRequestUrl: String,
    @SerializedName("title") val pullRequestTitle: String,
    @SerializedName("body") val pullRequestBody: String,
    @SerializedName("user") val userInfo: UserEntity
)
