package com.anderson.apigithub_mvvm.data.response

import com.google.gson.annotations.SerializedName

/**
 * Created by anderson on 22/09/19.
 */
data class OwnerResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val login: String
)