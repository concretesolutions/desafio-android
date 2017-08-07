package br.com.concrete.sdk.model

import android.os.Parcel
import br.com.concrete.sdk.extension.KParcelable
import br.com.concrete.sdk.extension.parcelableCreator
import com.google.gson.annotations.Expose

data class User(
        @Expose val login: String,
        @Expose val id: Long,
        @Expose val avatarUrl: String,
        @Expose val score: Int
) : KParcelable {
    companion object {
        @JvmField val CREATOR = parcelableCreator(::User)
    }

    private constructor(source: Parcel) : this(
            source.readString(),
            source.readLong(),
            source.readString(),
            source.readInt()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(login)
        writeLong(id)
        writeString(avatarUrl)
        writeInt(score)
    }
}