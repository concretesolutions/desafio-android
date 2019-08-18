package dev.theuzfaleiro.trendingongithub.ui.feature.home.model.response

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName(value = "avatar_url")
    val avatarUrl: String,
    @SerializedName(value = "login")
    val userName: String
)