package com.example.eloyvitorio.githubjavapop.data.model

import com.google.gson.annotations.SerializedName

class Repository(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("full_name")
        val fullName: String,
        @SerializedName("owner")
        val owner: Owner,
        @SerializedName("description")
        val description: String,
        @SerializedName("stargazers_count")
        val stargazersCount: Int,
        @SerializedName("forks")
        val forks: Int
)