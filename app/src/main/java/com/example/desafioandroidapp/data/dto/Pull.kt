package com.example.desafioandroidapp.data.dto

import com.google.gson.annotations.SerializedName

data class Pull(
    @SerializedName("url")
    val url: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("user")
    val user: User
)
