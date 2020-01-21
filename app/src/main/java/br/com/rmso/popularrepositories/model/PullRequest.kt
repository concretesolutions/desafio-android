package br.com.rmso.popularrepositories.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PullRequest (
    var title: String,
    @SerializedName("user")
    var owner: Owner,
    var created_at: String,
    var body: String,
    var html_url: String
): Parcelable