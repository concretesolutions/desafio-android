package com.example.eloyvitorio.githubjavapop.data.model

import com.google.gson.annotations.SerializedName

class UserPullRequest(
        @SerializedName("login")
        var login: String,
        @SerializedName("id")
        var id: Int,
        @SerializedName("avatar_url")
        var avatarUrl: String
)
