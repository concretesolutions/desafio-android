package br.com.henriqueoliveira.desafioandroidconcrete.service.models

import com.google.gson.annotations.SerializedName

data class Repository(
        @SerializedName("name") val repoName: String,
        @SerializedName("full_name") val fullName: String,
        @SerializedName("description") val description: String,
        @SerializedName("owner") val owner: User,
        @SerializedName("forks_count") val forksCount: Int,
        @SerializedName("stargazers_count") val starsCount: Int
)