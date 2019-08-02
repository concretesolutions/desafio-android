package wilquer.lima.desafioconcrete.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PullRequest(
    val html_url: String,
    @SerializedName("user") val user: Owner,
    val title: String,
    val created_at: String,
    val body: String
) : Serializable