package com.hotmail.fignunes.skytestefabionunes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitRepository(
    var total_count: Long,
    var incomplete_results: Boolean,
    var items: List<GitRepositoryItem>
) : Parcelable