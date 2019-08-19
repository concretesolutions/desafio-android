package com.joaodomingos.desafio_android.models

import com.google.gson.annotations.SerializedName

data class OwnerModel (
    @SerializedName("id") val id: Long,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("login") val userName: String
)