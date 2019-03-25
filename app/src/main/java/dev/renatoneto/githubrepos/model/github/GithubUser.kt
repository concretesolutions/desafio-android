package dev.renatoneto.githubrepos.model.github

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(@SerializedName("login") val login: String,
                      @SerializedName("avatar_url") val avatar: String): Parcelable