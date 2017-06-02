package br.com.concrete.sdk.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

data class Repo(
        @Expose val id: Long,
        @Expose val name: String,
        @Expose val fullName: String,
        @Expose val description: String,
        @Expose val forks: Long,
        @Expose val stargazersCount: Long,
        @Expose val owner: User
) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Repo> = object : Parcelable.Creator<Repo> {
            override fun createFromParcel(source: Parcel): Repo = Repo(source)
            override fun newArray(size: Int): Array<Repo?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readLong(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readLong(),
            source.readLong(),
            source.readParcelable<User>(User::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(name)
        dest.writeString(fullName)
        dest.writeString(description)
        dest.writeLong(forks)
        dest.writeLong(stargazersCount)
        dest.writeParcelable(owner, 0)
    }
}