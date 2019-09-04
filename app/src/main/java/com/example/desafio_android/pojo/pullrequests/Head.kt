package com.example.desafio_android.pojo.pullrequests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Head {

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
    var user: User_? = null
    @SerializedName("repo")
    @Expose
    var repo: Repo? = null

}
