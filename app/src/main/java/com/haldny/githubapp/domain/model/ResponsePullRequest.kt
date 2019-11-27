package com.haldny.githubapp.domain.model

import com.google.gson.annotations.SerializedName

data class ResponsePullRequest(@SerializedName("id") val id: Int,
                               @SerializedName("title") val title: String,
                               @SerializedName("body") val body: String,
                               @SerializedName("state") val state: String,
                               @SerializedName("html_url") val url: String,
                               @SerializedName("updated_at") val date: String,
                               @SerializedName("owner") val owner: Owner)