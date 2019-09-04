package com.example.desafio_android.pojo.pullrequests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Base {

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
    var user: User__? = null
    @SerializedName("repo")
    @Expose
    var repo: Repo_? = null

}
