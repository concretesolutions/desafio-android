package com.example.consultor.testacc.data.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    @Expose
    val name:String="",
    @SerializedName("avatar_url")
    @Expose
    val avatar:String=""
)
