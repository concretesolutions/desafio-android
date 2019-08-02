package wilquer.lima.desafioconcrete.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class PullRequest(
    val html_url: String,
    @SerializedName("user") val user: Owner,
    val title: String,
    val created_at: String,
    val body: String
) : Parcelable