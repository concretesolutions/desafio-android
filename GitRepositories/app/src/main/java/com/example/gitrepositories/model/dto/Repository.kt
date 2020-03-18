package com.example.gitrepositories.model.dto

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName("pull_requests") val repositories: List<Repository>
)

data class Repository(
    val title: String,
    val description: String,
    val username: String,
    val completeName: String,
    val starCount: Int,
    val forkCount: Int,
    val image: Bitmap?
)