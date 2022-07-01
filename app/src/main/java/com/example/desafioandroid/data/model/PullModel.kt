package com.example.desafioandroid.data.model

import com.google.gson.annotations.SerializedName

data class PullModel(
    @SerializedName("id") val id_pull: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
    @SerializedName("number") val number: Int,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("user") val user: OwnerModel
)
