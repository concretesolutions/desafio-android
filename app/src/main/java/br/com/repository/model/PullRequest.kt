package br.com.repository.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class PullRequest(@SerializedName("html_url")
                  var html_url: String,
                  @SerializedName("user")
                  var owner: Owner,
                  @SerializedName("title")
                  var title: String,
                  @SerializedName("body")
                  var body: String,
                  @SerializedName("created_at")
                  var date: String) : Parcelable