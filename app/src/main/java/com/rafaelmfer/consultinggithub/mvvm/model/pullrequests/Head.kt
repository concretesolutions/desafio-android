package com.rafaelmfer.consultinggithub.mvvm.model.pullrequests

import com.google.gson.annotations.SerializedName

data class Head(
    @SerializedName("label") val label: String,
    @SerializedName("ref") val ref: String,
    @SerializedName("sha") val sha: String,
    @SerializedName("user") val user: User,
    @SerializedName("repo") val repo: Repo
)