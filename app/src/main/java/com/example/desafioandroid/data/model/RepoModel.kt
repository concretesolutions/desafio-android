package com.example.desafioandroid.data.model

import com.google.gson.annotations.SerializedName

data class RepoModel(
    @SerializedName("id") val idRepo: Int,
    @SerializedName("name") val nameRepo: String,
    @SerializedName("description") val descriptionRepo: String,
    @SerializedName("full_name") val fullNameRepo: String,
    @SerializedName("owner") val owner_repos: OwnerModel,
    @SerializedName("stargazers_count") val stars: Int = 0,
    @SerializedName("forks_count") val forks: Int = 0,
)

