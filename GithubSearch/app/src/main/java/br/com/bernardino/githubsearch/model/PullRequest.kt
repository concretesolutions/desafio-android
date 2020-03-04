package br.com.bernardino.githubsearch.model

import com.google.gson.annotations.SerializedName

data class PullRequest (
    @SerializedName("url") val url : String,
    @SerializedName("id") val id : Int,
    @SerializedName("state") val state : String,
    @SerializedName("title") val title : String,
    @SerializedName("user") val user : User,
    @SerializedName("body") val body : String,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String,
    @SerializedName("closed_at") val closed_at : String,
    @SerializedName("merged_at") val merged_at : String
)