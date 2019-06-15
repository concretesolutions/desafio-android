package com.abs.javarepos.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PullRequest(
    val id: Int,
    val title: String,
    val body: String,
    @SerializedName("user") val owner: Owner,
    @SerializedName("created_at") val date: String,
    val html_url: String
) : Serializable