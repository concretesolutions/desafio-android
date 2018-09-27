package br.com.desafio.concrete.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


/**
 * Created by Malkes on 24/09/2018.
 */


data class User(
        @SerializedName("login") val login: String,
        @SerializedName("avatar_url") val avatarUrl: String
) : Parcelable {
        constructor(source: Parcel) : this(
                source.readString(),
                source.readString()
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
                writeString(login)
                writeString(avatarUrl)
        }

        companion object {
                @JvmField
                val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
                        override fun createFromParcel(source: Parcel): User = User(source)
                        override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
                }
        }
}