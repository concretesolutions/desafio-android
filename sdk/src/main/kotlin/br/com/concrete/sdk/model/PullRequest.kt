package br.com.concrete.sdk.model

import android.os.Parcel
import br.com.concrete.sdk.extension.KParcelable
import br.com.concrete.sdk.extension.parcelableCreator
import br.com.concrete.sdk.extension.readTypedObjectCompat
import br.com.concrete.sdk.extension.writeTypedObjectCompat
import br.com.concrete.sdk.model.type.State
import com.google.gson.annotations.Expose

data class PullRequest(
        @Expose val id: Long,
        @get:State
        @Expose @State val state: String,
        @Expose val title: String,
        @Expose val user: User,
        @Expose val body: String?
) : KParcelable {
    companion object {
        @JvmField val CREATOR = parcelableCreator(::PullRequest)
    }

    private constructor(source: Parcel) : this(
            source.readLong(),
            source.readString(),
            source.readString(),
            source.readTypedObjectCompat(User.CREATOR)!!,
            source.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(state)
        writeString(title)
        writeTypedObjectCompat(user, flags)
        writeString(body)
    }
}