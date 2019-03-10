package com.hako.githubapi.domain.entities

import com.google.gson.annotations.SerializedName

data class PullRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val description: String,
    @SerializedName("url")
    val url: String
)
