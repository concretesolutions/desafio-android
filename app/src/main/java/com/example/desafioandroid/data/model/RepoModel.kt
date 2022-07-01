package com.example.desafioandroid.data.model

import com.google.gson.annotations.SerializedName

data class RepoModel(
    @SerializedName("id") val id_repo: Int,
    @SerializedName("name") val name_repo: String,
    @SerializedName("stargazers_count") val stars: Int,
    @SerializedName("forks_count") val forks_count: Int,
    @SerializedName("owner") val owner_repo: OwnerModel
)
