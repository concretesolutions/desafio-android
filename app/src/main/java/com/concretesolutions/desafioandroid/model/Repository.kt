package com.concretesolutions.desafioandroid.model

import com.google.gson.annotations.SerializedName

data class Repository (
    @SerializedName("id")
    var id: Number,
    @SerializedName("forks_count")
    var forksCount:Number,
    @SerializedName("stargazers_count")
    var starsCount: Number,
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("owner")
    var owner: Owner
)