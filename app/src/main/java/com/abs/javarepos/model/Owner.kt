package com.abs.javarepos.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Owner(
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
) : Serializable