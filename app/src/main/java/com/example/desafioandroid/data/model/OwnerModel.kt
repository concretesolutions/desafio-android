package com.example.desafioandroid.data.model

import com.google.gson.annotations.SerializedName

data class OwnerModel(
    @SerializedName("id") val id_owner: Int,
    @SerializedName("avatar_url") val avatar_url_owner: String,
    @SerializedName("login") val login_owner: String
)