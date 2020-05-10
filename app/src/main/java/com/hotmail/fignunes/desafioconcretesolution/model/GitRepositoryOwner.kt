package com.hotmail.fignunes.skytestefabionunes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitRepositoryOwner(
    val login: String,
    val id: Long,
    val avatar_url: String,
    val url: String?,
    var name: String,
    var image: String
) : Parcelable