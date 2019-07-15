package com.pedrenrique.githubapp.core.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class PullRequest(
    val number: Int,
    val title: String,
    val body: String,
    val user: User,
    @SerializedName("html_url")
    val url: String,
    val createdAt: Date
)