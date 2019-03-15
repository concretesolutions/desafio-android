package cl.getapps.githubjavarepos.features.repopullrequests.domain.model


data class PullRequests(
    val pullrequests: List<PullRequestModel>
)

data class PullRequestModel(
    val user: User,
    val title: String,
    val body: String,
    val created_at: String
)

data class User(
    val login: String,
    val avatarUrl: String
)
