package com.hotmail.fignunes.desafioconcretesolution.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PullRequestUser(
    val login: String,
    val id: Long,
    val avatar_url: String,
    val url: String,
    var name: String,
    var image: String
): Parcelable