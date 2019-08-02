package br.com.concrete.githubconcretechallenge.features.repositories.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(val login: String,
                     @SerializedName("avatar_url") val avatarUrl: String) : Parcelable
//TODO check if it is possible to get username without making another request