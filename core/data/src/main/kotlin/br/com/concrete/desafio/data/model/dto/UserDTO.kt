package br.com.concrete.desafio.data.model.dto

import android.os.Parcel
import br.com.concrete.desafio.data.extension.KParcelable
import br.com.concrete.desafio.data.extension.parcelableCreator
import com.google.gson.annotations.Expose

data class UserDTO(
        @Expose val login: String,
        @Expose val id: Long,
        @Expose val avatarUrl: String,
        @Expose val score: Int
) : KParcelable {
    companion object {
        @JvmField val CREATOR = parcelableCreator(::UserDTO)
    }

    private constructor(source: Parcel) : this(
            source.readString() ?: "",
            source.readLong(),
            source.readString() ?: "",
            source.readInt()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(login)
        writeLong(id)
        writeString(avatarUrl)
        writeInt(score)
    }
}