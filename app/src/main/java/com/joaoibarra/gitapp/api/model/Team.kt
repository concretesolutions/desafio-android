package com.joaoibarra.gitapp.api.model

import com.google.gson.annotations.SerializedName

class Team (
    @SerializedName("id") val id: Int,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("url") val url: String,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("description") val description: String,
    @SerializedName("privacy") val privacy: String,
    @SerializedName("permission") val permission: String,
    @SerializedName("members_url") val membersUrl: String,
    @SerializedName("repositories_url") val repositoriesUrl: String,
    @SerializedName("parent") val parent: String
)