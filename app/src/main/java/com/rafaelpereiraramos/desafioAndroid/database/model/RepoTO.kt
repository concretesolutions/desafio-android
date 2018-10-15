package com.rafaelpereiraramos.desafioAndroid.database.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName

/**
 * Created by Rafael P. Ramos on 13/10/2018.
 */
@Entity(tableName = "repos",
        indices = [Index(value = ["owner_login"], unique = true)])
class RepoTO() : Parcelable {

    @Ignore constructor(description: String, forks: Int, fullName: String, id: String, name: String, stargazers: Int) : this() {
        this.description = description
        this.forks = forks
        this.fullName = fullName
        this.id = id
        this.name = name
        this.stargazers = stargazers
    }

    @SerializedName("description")
    lateinit var description: String

    @SerializedName("forks_count")
    var forks: Int = 0

    @SerializedName("full_name")
    lateinit var fullName: String

    @PrimaryKey
    @SerializedName("id")
    lateinit var id: String

    @SerializedName("name")
    lateinit var name: String

    @Embedded(prefix = "owner_")
    @SerializedName("owner")
    lateinit var owner: Owner

    @SerializedName("stargazers_count")
    var stargazers: Int = 0

    constructor(parcel: Parcel) : this() {
        description = parcel.readString()
        forks = parcel.readInt()
        fullName = parcel.readString()
        id = parcel.readString()
        name = parcel.readString()
        owner = parcel.readParcelable(Owner::class.java.classLoader)
        stargazers = parcel.readInt()
    }

    class Owner() : Parcelable {

        @Ignore constructor(avatarUrl: String, id: String, login: String) : this() {
            this.avatarUrl = avatarUrl
            this.login = login
        }

        @SerializedName("avatar_url")
        lateinit var avatarUrl: String

        @SerializedName("id")
        var id: String? = null

        @SerializedName("login")
        lateinit var login: String

        constructor(parcel: Parcel) : this() {
            avatarUrl = parcel.readString()
            login = parcel.readString()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(avatarUrl)
            parcel.writeString(login)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Owner> {
            override fun createFromParcel(parcel: Parcel): Owner {
                return Owner(parcel)
            }

            override fun newArray(size: Int): Array<Owner?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeInt(forks)
        parcel.writeString(fullName)
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeParcelable(owner, 1)
        parcel.writeInt(stargazers)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RepoTO> {
        override fun createFromParcel(parcel: Parcel): RepoTO {
            return RepoTO(parcel)
        }

        override fun newArray(size: Int): Array<RepoTO?> {
            return arrayOfNulls(size)
        }
    }
}