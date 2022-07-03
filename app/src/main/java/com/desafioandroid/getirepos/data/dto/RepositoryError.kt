package com.desafioandroid.getirepos.data.dto

import com.google.gson.annotations.SerializedName
import com.desafioandroid.getirepos.data.dto.Error

data class RepositoryError(
    @SerializedName("message")
    val message: String,
    @SerializedName("errors")
    val errors: Error?
)
