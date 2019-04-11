package com.example.desafioandroid.schema

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PullItem {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("number")
    @Expose
    var number: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("user")
    @Expose
    var user: User? = null
    @SerializedName("body")
    @Expose
    var body: String? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null
}
