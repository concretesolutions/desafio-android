package com.concretesolutions.desafioandroid.model

import com.google.gson.annotations.SerializedName

data class Owner (
    @SerializedName("id")
    var id: Number,
    @SerializedName("avatar")
    var login: String,
    @SerializedName("avatar_url")
    var avatarUrl: String
)