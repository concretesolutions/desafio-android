package br.com.rmso.popularrepositories.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository (
    var name: String,
    var full_name: String,
    var description: String,
    var owner: Owner,
    var stargazers_count: Int,
    var forks: Int

): Parcelable