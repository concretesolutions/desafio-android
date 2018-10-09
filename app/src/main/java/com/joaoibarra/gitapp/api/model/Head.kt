package com.joaoibarra.gitapp.api.model

import com.google.gson.annotations.SerializedName

class Head (
    @SerializedName("label") val label: String,
    @SerializedName("ref") val ref: String,
    @SerializedName("sha") val sha: String,
    @SerializedName("user") val user: User,
    @SerializedName("repo") val repo: Repo
)
