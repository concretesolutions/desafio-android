package com.example.consultor.testacc.data.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Owner(
    @SerializedName("login")
    @Expose
    val name:String="",
    @SerializedName("avatar_url")
    @Expose
    val avatar:String=""
):Serializable