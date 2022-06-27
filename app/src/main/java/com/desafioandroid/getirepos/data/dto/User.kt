package com.desafioandroid.getirepos.data.dto

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id : Long,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("gravatar_url")
    val gravatarUrl: String
)
