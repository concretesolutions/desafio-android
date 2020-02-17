package com.concrete.desafio_android.data.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
class PullRequest(
    val user: User,
    val title: String,
    val body: String,
    val html_url: String,
    val created_at: Date
): Parcelable