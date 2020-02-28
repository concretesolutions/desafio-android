package br.com.bernardino.githubsearch.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

const val EXTRA_REPOSITORY = "repository_extra"

data class RepositoryBody(
    val items: ArrayList<Repository>
)

@Parcelize
data class Repository constructor(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("description")
    val description: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int
) : Parcelable