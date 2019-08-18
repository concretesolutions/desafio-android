package dev.theuzfaleiro.trendingongithub.ui.feature.home.model.response

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "name")
    val name: String,
    @SerializedName(value = "description")
    val description: String?,
    @SerializedName(value = "owner")
    val owner: Owner,
    @SerializedName(value = "stargazers_count")
    val starsCount: Int,
    @SerializedName(value = "forks")
    val forksCount: Int
)