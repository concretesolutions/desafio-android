package com.uharris.desafio_android.domain.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Repository(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val owner: User = User(),
    val forks: Int = 0,
    @SerializedName("stargazers_count") val stars: Int = 0
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString() ?: "",
        source.readString() ?: "",
        source.readParcelable<User>(User::class.java.classLoader) ?: User(),
        source.readInt(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(name)
        writeString(description)
        writeParcelable(owner, 0)
        writeInt(forks)
        writeInt(stars)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Repository> = object : Parcelable.Creator<Repository> {
            override fun createFromParcel(source: Parcel): Repository = Repository(source)
            override fun newArray(size: Int): Array<Repository?> = arrayOfNulls(size)
        }
    }
}