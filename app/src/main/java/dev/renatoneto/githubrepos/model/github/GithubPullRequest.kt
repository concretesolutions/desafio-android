package dev.renatoneto.githubrepos.model.github

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubPullRequest(@SerializedName("title") val title: String,
                             @SerializedName("body") val body: String,
                             @SerializedName("number") val number: Int,
                             @SerializedName("html_url") val htmlUrl: String,
                             @SerializedName("user") val user: GithubUser): Parcelable