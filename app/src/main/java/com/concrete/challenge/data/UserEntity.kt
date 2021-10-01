package com.concrete.challenge.data

import com.google.gson.annotations.SerializedName

data class UserEntity(
    @SerializedName("login") val username: String,
    @SerializedName("id") val userId: String,
    @SerializedName("avatar_url") val userAvatarUrl: String,
    @SerializedName("name") val userName: String
)