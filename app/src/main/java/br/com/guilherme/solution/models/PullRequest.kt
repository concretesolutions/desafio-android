package br.com.guilherme.solution.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PullRequest(
    val url: String,
    val title: String,
    val user: User,
    val body: String
) : Parcelable