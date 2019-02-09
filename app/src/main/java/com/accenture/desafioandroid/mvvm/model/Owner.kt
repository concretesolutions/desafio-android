package com.accenture.desafioandroid.mvvm.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Owner {

    @SerializedName("login")
    @Expose
    var login: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null


}