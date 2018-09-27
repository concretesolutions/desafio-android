package br.com.desafio.concrete.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


/**
 * Created by Malkes on 24/09/2018.
 */


data class PullRequest(
        @SerializedName("url") val url: String,
        @SerializedName("title") val title: String,
        @SerializedName("user") val user: User,
        @SerializedName("created_at") val createdAt: String,
        @SerializedName("body") val body: String,
        @SerializedName("state") val state: String
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(User::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(title)
        parcel.writeParcelable(user, flags)
        parcel.writeString(createdAt)
        parcel.writeString(body)
        parcel.writeString(state)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PullRequest> {
        override fun createFromParcel(parcel: Parcel): PullRequest {
            return PullRequest(parcel)
        }

        override fun newArray(size: Int): Array<PullRequest?> {
            return arrayOfNulls(size)
        }
    }
}
