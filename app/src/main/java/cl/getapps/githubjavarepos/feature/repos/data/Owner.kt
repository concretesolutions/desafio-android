package cl.getapps.githubjavarepos.feature.repos.data

import cl.getapps.githubjavarepos.core.extension.DataOwner
import cl.getapps.githubjavarepos.core.extension.DomainOwner
import com.google.gson.annotations.SerializedName


data class Owner(
    @SerializedName("login")
    val login: String,
    val id: Int,
    val node_id: String,
    @SerializedName("avatar_url")
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val events_url: String,
    val received_events_url: String,
    val type: String,
    val site_admin: Boolean
) {
    fun toDomainOwner(dataOwner: DataOwner) = DomainOwner(dataOwner.login, dataOwner.avatar_url)
}