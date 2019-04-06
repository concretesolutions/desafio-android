package com.example.desafioandroid.schemas

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class BasePull {

    @SerializedName("label")
    @Expose
    var label: String? = null
    @SerializedName("ref")
    @Expose
    var ref: String? = null
    @SerializedName("sha")
    @Expose
    var sha: String? = null
    @SerializedName("user")
    @Expose
    var user: User? = null
    @SerializedName("repo")
    @Expose
    var repo: Repo? = null

}