package com.example.desafioandroid.data.model

import com.google.gson.annotations.SerializedName

data class RepositoriesModel(
    @SerializedName("id") val id_repos: Int,
    @SerializedName("name") val name_repos: String,
    @SerializedName("description") val description_repos: String,
    @SerializedName("owner") val owner_repos: OwnerModel,
    val star: RepoModel
) {
    operator fun get(index: Int): Any = Unit
} 

