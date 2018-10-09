package br.com.repository.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Repository(@SerializedName("name")
                 var nameRepository: String,
                 @SerializedName("description")
                 var descRepository: String,
                 @SerializedName("watchers")
                 var numberStar: Int,
                 @SerializedName("forks")
                 var numberFork: Int,
                 @SerializedName("owner")
                 var owner: Owner) : Parcelable