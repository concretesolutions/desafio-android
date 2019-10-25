package com.example.eloyvitorio.githubjavapop.data.model

import com.google.gson.annotations.SerializedName

class Owner(
        @SerializedName("login")
        val login: String,
        @SerializedName("avatar_url")
        val avatarUrl: String
)
