package com.concrete.desafioandroid.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class RequestRepo(
        val items: List<Repo>
)

@Parcelize
data class Repo(
        val id: String,
        val name: String,
        val description: String,
        @SerializedName("stargazers_count")
        val starsCount: Int,
        @SerializedName("forks")
        val forksCount: Int,
        @SerializedName("pulls_url")
        val pullsUrl: String,
        @SerializedName("owner")
        val ownerOverview: RequestOwner
): Parcelable {
    var ownerDetails: OwnerDetails? = null
}

@Parcelize
data class RequestOwner(
        val url: String,
        val login: String,
        @SerializedName("avatar_url")
        val avatarIcon: String
): Parcelable

@Parcelize
data class OwnerDetails(
        val id: String,
        val name: String,
        val login: String,
        val email: String,
        val company: String,
        val url: String,
        @SerializedName("avatar_url")
        val avatarUrl: String
): Parcelable

@Parcelize
data class PullRequest(
        val title: String,
        val body: String,
        @SerializedName("created_at")
        val createdAt: String,
        val state: String,
        @SerializedName("user")
        val userInfo: RequestOwner,
        @SerializedName("html_url")
        val htmlUrl: String
): Parcelable
