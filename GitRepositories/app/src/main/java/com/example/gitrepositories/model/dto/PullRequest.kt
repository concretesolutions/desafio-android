package com.example.gitrepositories.model.dto

import com.google.gson.annotations.SerializedName

data class PullRequest(
    @SerializedName("title")
    val title: String,

    @SerializedName("body")
    val description: String,

    @SerializedName("url")
    val link: String,

    @SerializedName("created_at")
    val date: String,

    @SerializedName("user")
    val user: User
)