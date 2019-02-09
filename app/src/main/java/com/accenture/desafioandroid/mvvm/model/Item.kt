package com.accenture.desafioandroid.mvvm.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item(
    @SerializedName("id")
    @Expose var id: Int?,
    @SerializedName("node_id")
    @Expose var nodeId: String?,
    @SerializedName("name")
    @Expose var name: String?,
    @SerializedName("owner")
    @Expose var owner: Owner?,
    @SerializedName("description")
    @Expose var description: String?,
    @SerializedName("url")
    @Expose var url: String?,
    @SerializedName("stargazers_count")
    @Expose var stargazersCount: Int?,
    @SerializedName("forks_count")
    @Expose var forksCount: Int?
)