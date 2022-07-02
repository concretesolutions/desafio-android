package com.example.desafioandroid.data.model

import com.google.gson.annotations.SerializedName

data class OwnerModel(
    @SerializedName("id") val idOwner: Int,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("login") val loginOwner: String
)