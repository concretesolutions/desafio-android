package com.example.desafioandroidapp.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val login: String,
    val id: Long,
    val nodeId: String,
    val avatarUrl: String,
) : Parcelable
