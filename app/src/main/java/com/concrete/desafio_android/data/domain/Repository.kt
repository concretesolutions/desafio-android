package com.concrete.desafio_android.data.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Repository(
    val name: String,
    val description: String,
    val stargazers_count: Int,
    val forks_count: Int,
    val owner: User
) : Parcelable