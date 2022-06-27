package com.desafioandroid.getirepos.data.dto

import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName("id")
    val id: Long,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("pulls_url")
    val pullsUrl: String,
    @SerializedName("owner")
    val owner: Owner
)
