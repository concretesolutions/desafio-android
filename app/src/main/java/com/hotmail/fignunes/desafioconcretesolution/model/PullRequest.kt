package com.hotmail.fignunes.desafioconcretesolution.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PullRequest(
    val id: Long,
    val title: String,
    val user: PullRequestUser,
    val body: String,
    val created_at: String,
    val state: String,
    val html_url: String
): Parcelable