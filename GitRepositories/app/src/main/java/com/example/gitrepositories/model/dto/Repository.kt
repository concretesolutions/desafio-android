package com.example.gitrepositories.model.dto

import android.graphics.Bitmap

data class Repository(
    val title: String,
    val description: String,
    val username: String,
    val completeName: String,
    val starCount: Int,
    val forkCount: Int,
    val image: Bitmap?
)