package br.com.concrete.sdk.model

import android.os.Parcel
import android.os.Parcelable

data class User(
        val login: String,
        val id: Long,
        val avatarUrl: String,
        val score: Int
) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readLong(),
            source.readString(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(login)
        dest.writeLong(id)
        dest.writeString(avatarUrl)
        dest.writeInt(score)
    }
}