package com.example.eloyvitorio.githubjavapop.data.model

import com.google.gson.annotations.SerializedName

class HeadPullRequest (
    @SerializedName("repo")
    val repo: RepoPullRequest
)