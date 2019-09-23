package com.anderson.apigithub_mvvm.data.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    var login: String,
    @SerializedName("avatar_url")
    var avatarUrl: String
)