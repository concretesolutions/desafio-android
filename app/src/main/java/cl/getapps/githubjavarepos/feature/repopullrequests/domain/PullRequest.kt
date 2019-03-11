package cl.getapps.githubjavarepos.feature.repopullrequests.domain


data class PullRequest(
    val user: User,
    val title: String,
    val body: String,
    val created_at: String
)