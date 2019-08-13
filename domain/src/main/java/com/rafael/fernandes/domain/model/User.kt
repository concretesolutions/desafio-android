package com.rafael.fernandes.domain.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val login: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("bio")
    val bio: String? = null
)
