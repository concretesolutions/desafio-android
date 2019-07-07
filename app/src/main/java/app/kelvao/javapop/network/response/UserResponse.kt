package app.kelvao.javapop.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse(
    val login: String,
    val name: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
) : Parcelable