package com.example.desafioandroid.data.model

import com.google.gson.annotations.SerializedName

data class RepositoriesModel(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("owner") val owner: OwnerModel
)
