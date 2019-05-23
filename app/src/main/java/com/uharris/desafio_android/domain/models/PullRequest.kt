package com.uharris.desafio_android.domain.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PullRequest(
    val id: Int = 0,
    val title: String = "",
    val body: String = "",
    val user: User = User(),
    @SerializedName("html_url") val url: String = ""
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString() ?: "",
        source.readString() ?: "",
        source.readParcelable<User>(User::class.java.classLoader) ?: User(),
        source.readString() ?: ""
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(title)
        writeString(body)
        writeParcelable(user, 0)
        writeString(url)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<PullRequest> = object : Parcelable.Creator<PullRequest> {
            override fun createFromParcel(source: Parcel): PullRequest = PullRequest(source)
            override fun newArray(size: Int): Array<PullRequest?> = arrayOfNulls(size)
        }
    }
}