package com.joaoibarra.gitapp.api.model

import com.google.gson.annotations.SerializedName

class Label (
    @SerializedName("id") val id: Int,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("url") val url: String,
    @SerializedName("name") val name: String,
    @SerializedName("color") val color: String,
    @SerializedName("default") val default: Boolean
)
