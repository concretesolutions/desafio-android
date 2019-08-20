package com.concrete.desafio.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Autor(


    @Expose
    @SerializedName("id")
    val id: Long,

    @Expose
    @SerializedName("login")
    val login: String,

    @Expose
    @SerializedName("avatar_url")
    val avatar: String

)