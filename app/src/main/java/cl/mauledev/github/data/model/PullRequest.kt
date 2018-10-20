package cl.mauledev.github.data.model

import com.google.gson.annotations.SerializedName
import java.util.*


data class PullRequest(@SerializedName("id") var id: String,
                       var title: String? = null,
                       @SerializedName("state") var state: String? = null,
                       @SerializedName("html_url") var url: String? = null,
                       var user: User? = null,
                       @SerializedName("created_at") var createdAt: Date) {

    constructor() : this("", null, null, null, null, Date())

    override fun equals(other: Any?): Boolean {
        if (other == null)
            return false
        if (other !is PullRequest)
            return false
        if (other.id != this.id)
            return false

        return true
    }

    override fun hashCode(): Int {
        var hash = 7
        hash = 31 * hash + id.hashCode()
        return hash
    }
}