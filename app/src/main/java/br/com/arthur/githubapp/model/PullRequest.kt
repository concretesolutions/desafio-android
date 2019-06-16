package br.com.arthur.githubapp.model

import com.google.gson.annotations.SerializedName

class PullRequest(

    @SerializedName("body")
    val body: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("diff_url")
    val diffUrl: String,

    @SerializedName("html_url")
    val htmlUrl: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("issue_url")
    val issueUrl: String,

    @SerializedName("locked")
    val locked: Boolean,

    @SerializedName("node_id")
    val nodeId: String,

    @SerializedName("number")
    val number: Int,

    @SerializedName("patch_url")
    val patchUrl: String,

    @SerializedName("state")
    val state: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("user")
    val user: Owner
)
