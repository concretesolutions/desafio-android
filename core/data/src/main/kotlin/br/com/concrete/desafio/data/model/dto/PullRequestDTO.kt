package br.com.concrete.desafio.data.model.dto

import android.os.Parcel
import br.com.concrete.desafio.data.extension.KParcelable
import br.com.concrete.desafio.data.extension.parcelableCreator
import br.com.concrete.desafio.data.extension.readTypedObjectCompat
import br.com.concrete.desafio.data.extension.writeTypedObjectCompat
import com.google.gson.annotations.Expose

data class PullRequestDTO(
        @Expose val id: Long,
        @Expose val title: String,
        @Expose val user: UserDTO,
        @Expose val body: String?
) : KParcelable {
    companion object {
        @JvmField val CREATOR = parcelableCreator(::PullRequestDTO)
    }

    private constructor(source: Parcel) : this(
            source.readLong(),
            source.readString() ?: "",
            source.readTypedObjectCompat(UserDTO.CREATOR)!!,
            source.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(title)
        writeTypedObjectCompat(user, flags)
        writeString(body)
    }
}