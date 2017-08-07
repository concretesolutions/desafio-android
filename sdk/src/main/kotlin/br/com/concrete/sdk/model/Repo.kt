package br.com.concrete.sdk.model

import android.os.Parcel
import br.com.concrete.sdk.extension.KParcelable
import br.com.concrete.sdk.extension.parcelableCreator
import br.com.concrete.sdk.extension.readTypedObjectCompat
import br.com.concrete.sdk.extension.writeTypedObjectCompat
import com.google.gson.annotations.Expose

data class Repo(
        @Expose val id: Long,
        @Expose val name: String,
        @Expose val fullName: String,
        @Expose val description: String?,
        @Expose val forks: Long,
        @Expose val stargazersCount: Long,
        @Expose val owner: User
) : KParcelable {
    companion object {
        @JvmField val CREATOR = parcelableCreator(::Repo)
    }

    private constructor(source: Parcel) : this(
            source.readLong(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readLong(),
            source.readLong(),
            source.readTypedObjectCompat(User.CREATOR)!!
    )

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(name)
        writeString(fullName)
        writeString(description)
        writeLong(forks)
        writeLong(stargazersCount)
        writeTypedObjectCompat(owner, flags)
    }
}