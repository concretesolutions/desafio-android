package br.com.guilherme.solution.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response(
    val total_count: Long,
    val incomplete_results: Boolean,
    val items: List<Repository>
) : Parcelable