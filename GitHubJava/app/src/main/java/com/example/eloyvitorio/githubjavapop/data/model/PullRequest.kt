package com.example.eloyvitorio.githubjavapop.data.model

import com.google.gson.annotations.SerializedName

class PullRequest (
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("user")
    val user: UserPullRequest,
    @SerializedName("body")
    val body: String,
    @SerializedName("head")
    val head: HeadPullRequest
)
