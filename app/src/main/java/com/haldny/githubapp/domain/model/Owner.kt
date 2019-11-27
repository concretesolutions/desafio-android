package com.haldny.githubapp.domain.model

import com.google.gson.annotations.SerializedName

data class Owner(@SerializedName("id") val id: Int,
                 @SerializedName("login") val login: String,
                 @SerializedName("avatar_url") val avatarUrl: String)