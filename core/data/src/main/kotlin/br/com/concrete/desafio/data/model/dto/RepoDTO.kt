package br.com.concrete.desafio.data.model.dto

import android.os.Parcel
import br.com.concrete.desafio.data.extension.KParcelable
import br.com.concrete.desafio.data.extension.parcelableCreator
import br.com.concrete.desafio.data.extension.readTypedObjectCompat
import br.com.concrete.desafio.data.extension.writeTypedObjectCompat
import com.google.gson.annotations.Expose

data class RepoDTO(
        @Expose val id: Long,
        @Expose val name: String,
        @Expose val fullName: String,
        @Expose val description: String?,
        @Expose val forks: Long,
        @Expose val stargazersCount: Long,
        @Expose val owner: UserDTO
) : KParcelable {
    companion object {
        @JvmField val CREATOR = parcelableCreator(::RepoDTO)
    }

    private constructor(source: Parcel) : this(
            source.readLong(),
            source.readString() ?: "",
            source.readString() ?: "",
            source.readString(),
            source.readLong(),
            source.readLong(),
            source.readTypedObjectCompat(UserDTO.CREATOR)!!
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