package com.example.gitrepositories.model.dto

import android.graphics.Bitmap
import java.util.*

data class PullRequest(
    val title: String,
    val description: String,
    val username: String,
    val completeName: String,
    val link: String,
    val date: Date,
    val image: Bitmap?
)