package com.example.gitrepositories.model.dto

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("name")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("stargazers_count")
    val starCount: Int,

    @SerializedName("forks_count")
    val forkCount: Int,

    @SerializedName("owner")
    val user: User
)