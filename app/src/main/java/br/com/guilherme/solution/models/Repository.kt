package br.com.guilherme.solution.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository(
    val id: Long,
    val name: String,
    val owner: Owner,
    val description: String,
    val stargazers_count: String
) : Parcelable