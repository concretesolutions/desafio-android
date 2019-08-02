package br.com.concrete.githubconcretechallenge.features.repositories.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepositoryModel(
    val id: Long,
    val name: String,
    val description: String,
    @SerializedName("forks_count") val forksCount: String,
    @SerializedName("stargazers_count") val starsCount: String,
    val owner: UserModel
) : Parcelable