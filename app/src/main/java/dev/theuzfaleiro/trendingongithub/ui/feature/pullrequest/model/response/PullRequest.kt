package dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.model.response

import com.google.gson.annotations.SerializedName

data class PullRequest(
    @SerializedName(value = "html_url")
    val url: String,
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "title")
    val title: String,
    @SerializedName(value = "body")
    val description: String,
    @SerializedName(value = "user")
    val user: User
)