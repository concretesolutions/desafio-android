package com.abs.javarepos.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDate

data class PullRequest(
    val id: Int,
    val title: String,
    val body: String,
    @SerializedName("user") val owner: Owner,
    @SerializedName("created_at") val date: LocalDate
) : Serializable