package com.ruiderson.desafio_android.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PullRequest (
    val id: Long,
    val title: String,
    val body: String,
    val state: String,
    val html_url: String,
    val user: PullRequestUser
) : Parcelable

@Parcelize
data class PullRequestUser(
    val avatar_url: String,
    val login: String
): Parcelable