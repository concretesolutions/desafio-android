package app.kelvao.javapop.domain.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepositoryResponse(
    val name: String,
    val description: String,
    val owner: UserResponse,
    @SerializedName("forks_count")
    val forksCount : Long,
    @SerializedName("stargazers_count")
    val stargazersCount : Long
) : Parcelable