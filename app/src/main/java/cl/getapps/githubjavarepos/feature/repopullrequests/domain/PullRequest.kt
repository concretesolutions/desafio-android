package cl.getapps.githubjavarepos.feature.repopullrequests.domain


data class PullRequests(
    val pullrequests: List<PullRequest>
)

data class PullRequest(
    val user: User,
    val title: String,
    val body: String,
    val created_at: String
)

data class User(
    val login: String,
    val avatarUrl: String
)
