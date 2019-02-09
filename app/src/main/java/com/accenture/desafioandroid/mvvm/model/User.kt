package com.accenture.desafioandroid.mvvm.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class User {

    @SerializedName("login")
    @Expose
    var login: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("node_id")
    @Expose
    var nodeId: String? = null
    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String? = null
}