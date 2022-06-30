package com.example.desafioandroid.data.model

import com.google.gson.annotations.SerializedName

data class OwnerModel(
    @SerializedName("avatar_url") val avatar_url: String,
    @SerializedName("login") val login: String
)