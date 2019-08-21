package dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.model.response

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName(value = "name")
    val fullName: String,
    @SerializedName(value = "login")
    val login: String,
    @SerializedName(value = "avatar_url")
    val avatarUrl: String
)