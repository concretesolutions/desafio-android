package com.pedrenrique.githubapp.core.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val avatarUrl: String,
    val login: String
) : Parcelable