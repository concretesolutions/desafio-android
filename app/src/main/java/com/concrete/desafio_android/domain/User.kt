package com.concrete.desafio_android.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class RepositoryOwner (
    val avatar_url: String,
    val login: String
): Parcelable