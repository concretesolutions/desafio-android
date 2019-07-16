package com.pedrenrique.githubapp.core.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository(
        val name: String,
        val fullName: String,
        val description: String?,
        val forksCount: Int,
        val stargazersCount: Int,
        val owner: User
) : Parcelable