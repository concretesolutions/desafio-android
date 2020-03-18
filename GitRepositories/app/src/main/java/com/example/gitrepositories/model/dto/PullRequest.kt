package com.example.gitrepositories.model.dto

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName
import java.util.*

data class PullRequestResponse(
    @SerializedName("pull_requests") val pullRequests: List<PullRequest>
)

data class PullRequest(
    val title: String,
    val description: String,
    val username: String,
    val completeName: String,
    val link: String,
    val date: Date,
    val image: Bitmap?
)