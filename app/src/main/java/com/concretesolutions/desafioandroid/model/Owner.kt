package com.concretesolutions.desafioandroid.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Owner (
    @SerializedName("id")
    var id: Int,
    @SerializedName("login")
    var login: String,
    @SerializedName("avatar_url")
    var avatarUrl: String
): Parcelable {

    companion object CREATOR : Parcelable.Creator<Owner> {
        override fun createFromParcel(parcel: Parcel): Owner {
            return Owner(parcel.readInt(), parcel.readString()!!, parcel.readString()!!)
        }

        override fun newArray(size: Int): Array<Owner?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            it.writeInt(id)
            it.writeString(login)
            it.writeString(avatarUrl)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

}