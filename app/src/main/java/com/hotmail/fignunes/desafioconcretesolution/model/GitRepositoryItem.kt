package com.hotmail.fignunes.skytestefabionunes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitRepositoryItem(
    val id: Long,
    val name: String,
    val full_name: String?,
    val owner: GitRepositoryOwner,
    val description: String?,
    val stargazers_count: Long?,
    val forks_count: Long?
) : Parcelable