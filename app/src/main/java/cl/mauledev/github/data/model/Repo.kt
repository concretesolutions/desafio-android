package cl.mauledev.github.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*


data class Repo(@SerializedName("id") var id: String,
                var name: String? = null,
                @SerializedName("html_url") var url: String? = null,
                @SerializedName("owner") var author: User? = null,
                @SerializedName("watchers") var stars: Int = 0,
                @SerializedName("forks") var forks: Int = 0,
                @SerializedName("created_at") var createdAt: Date): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(User::class.java.classLoader),
            parcel.readInt(),
            parcel.readInt(),
            Date(parcel.readLong()))

    constructor() : this("", null, null, null, 0, 0, Date())

    override fun equals(other: Any?): Boolean {
        if (other == null)
            return false
        if (other !is Repo)
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
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeInt(stars)
        parcel.writeInt(forks)
        parcel.writeLong(createdAt.time)
        parcel.writeParcelable(author, Parcelable.PARCELABLE_WRITE_RETURN_VALUE)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Repo> {
        override fun createFromParcel(parcel: Parcel): Repo {
            return Repo(parcel)
        }

        override fun newArray(size: Int): Array<Repo?> {
            return arrayOfNulls(size)
        }
    }
}