package br.com.concrete.sdk.model

import android.os.Parcel
import android.os.Parcelable
import br.com.concrete.sdk.model.type.State
import com.google.gson.annotations.Expose

data class PullRequest(
        @Expose val id: Long,
        @Expose @State val state: String,
        @Expose val title: String,
        @Expose val user: User,
        @Expose val body: String
) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<PullRequest> = object : Parcelable.Creator<PullRequest> {
            override fun createFromParcel(source: Parcel): PullRequest = PullRequest(source)
            override fun newArray(size: Int): Array<PullRequest?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readLong(),
            source.readString(),
            source.readString(),
            source.readParcelable<User>(User::class.java.classLoader),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(state)
        dest.writeString(title)
        dest.writeParcelable(user, 0)
        dest.writeString(body)
    }
}