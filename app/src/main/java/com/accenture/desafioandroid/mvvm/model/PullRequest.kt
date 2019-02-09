package com.accenture.desafioandroid.mvvm.model

import com.accenture.desafioandroid.mvvm.model.Assignee
import com.accenture.desafioandroid.mvvm.model.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PullRequest {

    @SerializedName("html_url")
    @Expose
    var url: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("node_id")
    @Expose
    var nodeId: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("user")
    @Expose
    var user: User? = null
    @SerializedName("body")
    @Expose
    var body: String? = null
    @SerializedName("updated_at")
    @Expose
    var updateUp: String? = null

}