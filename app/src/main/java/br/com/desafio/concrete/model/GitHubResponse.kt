package br.com.desafio.concrete.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Malkes on 24/09/2018.
 */


data class GitHubResponse (
                        @SerializedName("total_count") val totalCount: Int,
                        @SerializedName("items") val items: List<Repository>
            ) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.createTypedArrayList(Repository.CREATOR))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(totalCount)
        parcel.writeTypedList(items)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GitHubResponse> {
        override fun createFromParcel(parcel: Parcel): GitHubResponse {
            return GitHubResponse(parcel)
        }

        override fun newArray(size: Int): Array<GitHubResponse?> {
            return arrayOfNulls(size)
        }
    }
}