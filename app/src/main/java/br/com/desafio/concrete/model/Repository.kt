package br.com.desafio.concrete.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Malkes on 24/09/2018.
 */


data class Repository(
        @SerializedName("name") val repoName: String,
        @SerializedName("full_name") val fullName: String,
        @SerializedName("description") val description: String,
        @SerializedName("owner") val owner: User,
        @SerializedName("forks_count") val forksCount: Int,
        @SerializedName("stargazers_count") val starsCount: Int
) : Parcelable {
        constructor(source: Parcel) : this(
                source.readString(),
                source.readString(),
                source.readString(),
                source.readParcelable<User>(User::class.java.classLoader),
                source.readInt(),
                source.readInt()
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
                writeString(repoName)
                writeString(fullName)
                writeString(description)
                writeParcelable(owner, 0)
                writeInt(forksCount)
                writeInt(starsCount)
        }

        companion object {
                @JvmField
                val CREATOR: Parcelable.Creator<Repository> = object : Parcelable.Creator<Repository> {
                        override fun createFromParcel(source: Parcel): Repository = Repository(source)
                        override fun newArray(size: Int): Array<Repository?> = arrayOfNulls(size)
                }
        }
}