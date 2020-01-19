package com.concretesolutions.desafioandroid.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("id")
    var id: Int,
    @SerializedName("forks_count")
    var forksCount:Int,
    @SerializedName("stargazers_count")
    var starsCount: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("full_name")
    var fullName: String,
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("owner")
    var owner: Owner
): Parcelable {

    companion object CREATOR : Parcelable.Creator<Repository> {
        override fun createFromParcel(parcel: Parcel): Repository {
            return Repository(
                parcel.readInt(),
                parcel.readInt(),
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readParcelable(Owner.javaClass.classLoader)
            )
        }

        override fun newArray(size: Int): Array<Repository?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            it.writeInt(id)
            it.writeInt(forksCount)
            it.writeInt(starsCount)
            it.writeString(name)
            it.writeString(fullName)
            it.writeString(description)
            it.writeParcelable(owner, flags)
        }
    }

    override fun describeContents(): Int {
        return 0
    }
}