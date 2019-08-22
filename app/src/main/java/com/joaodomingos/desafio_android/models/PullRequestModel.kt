package com.joaodomingos.desafio_android.models

import com.google.gson.annotations.SerializedName

data class PullRequestModel (
    @SerializedName("title") val title: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("user") val user: OwnerModel,
    @SerializedName("body") val body: String,
    @SerializedName("number") val number: Long
)
