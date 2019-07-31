package app.kelvao.javapop.domain.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepositoriesResponse(
    val items: List<RepositoryResponse>
) : Parcelable