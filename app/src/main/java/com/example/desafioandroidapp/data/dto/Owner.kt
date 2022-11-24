package com.example.desafioandroidapp.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(
    val login: String,
    val id: Int,
    val avatar_url: String
) : Parcelable
