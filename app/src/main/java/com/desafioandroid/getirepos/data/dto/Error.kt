package com.desafioandroid.getirepos.data.dto

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("resource")
    val resource: String,
    @SerializedName("field")
    val field: String,
    @SerializedName("code")
    val code: String
)
