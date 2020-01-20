package com.concretesolutions.desafioandroid.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PullRequest(@SerializedName("title") val title: String,
                       @SerializedName("html_url") val url: String,
                       @SerializedName("body") val body: String,
                       @SerializedName("created_at") val createdData: String,
                       @SerializedName("user") val user: Owner) : Parcelable{

    companion object CREATOR : Parcelable.Creator<PullRequest> {
        override fun createFromParcel(parcel: Parcel): PullRequest {
            return PullRequest(
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readParcelable(Owner.javaClass.classLoader)!!
            )
        }

        override fun newArray(size: Int): Array<PullRequest?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            it.writeString(title)
            it.writeString(url)
            it.writeString(body)
            it.writeParcelable(user, flags)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

}