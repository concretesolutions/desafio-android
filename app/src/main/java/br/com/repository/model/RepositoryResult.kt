package br.com.repository.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class RepositoryResult(@SerializedName("items")
                         var repositoryList: List<Repository>) : Parcelable