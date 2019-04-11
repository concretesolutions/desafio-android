package com.example.desafioandroid.schema

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

}
