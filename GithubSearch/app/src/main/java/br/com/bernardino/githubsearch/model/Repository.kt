package br.com.bernardino.githubsearch.model

import com.google.gson.annotations.SerializedName

data class RepositoryBody(
    val items: ArrayList<Repository>
)

data class Repository constructor(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("description")
    val description: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int
)