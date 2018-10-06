package com.concrete.andresdavid.desafioandroid.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PullRequest(var type: String = "PULL_REQUEST") {
    @SerializedName("id")
    @Expose
    var id: Long = 0

    @SerializedName("title")
    @Expose
    var title: String = ""

    @SerializedName("html_url")
    @Expose
    var htmlUrl: String = ""

    @SerializedName("body")
    @Expose
    var body: String = ""

    @SerializedName("state")
    @Expose
    var state: String? = null

    @SerializedName("user")
    @Expose
    var user: User? = null
}