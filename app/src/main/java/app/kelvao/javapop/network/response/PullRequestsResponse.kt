package app.kelvao.javapop.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PullRequestsResponse(
    val title: String,
    val body: String,
    val user: UserResponse,
    @SerializedName("html_url")
    val url: String,
    private val _state: String
) : Parcelable {
    val state: State
        get() = State.valueOf(_state)

    enum class State(value: String) {
        OPEN("open"), CLOSE("close")
    }

}