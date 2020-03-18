package com.example.gitrepositories.model.dto

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("login")
    val username: String,

    @SerializedName("avatar_url")
    val image: String?
)