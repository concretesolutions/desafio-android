package cl.mauledev.github.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*


data class User(@SerializedName("id") var id: String,
                var login: String? = null,
                @SerializedName("avatar_url") var avatarUrl: String? = null,
                @SerializedName("type") var type: String? = null): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    constructor() : this("", null, null, null)

    override fun equals(other: Any?): Boolean {
        if (other == null)
            return false
        if (other !is User)
            return false
        if (other.id != this.id)
            return false

        return true
    }

    override fun hashCode(): Int {
        var hash = 7
        hash = 31 * hash + id.hashCode()
        return hash
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(login)
        parcel.writeString(avatarUrl)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}