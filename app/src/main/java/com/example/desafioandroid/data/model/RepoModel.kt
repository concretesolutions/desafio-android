package com.example.desafioandroid.data.model

import com.google.gson.annotations.SerializedName

data class RepoModel(
    @SerializedName("id") val id_repo: Int,
    @SerializedName("name") val name_repo: String,
    @SerializedName("owner") val owner_repo: OwnerModel
)
