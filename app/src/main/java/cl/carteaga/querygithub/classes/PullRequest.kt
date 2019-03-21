package cl.carteaga.querygithub.classes

data class PullRequest (
    var title: String,
    var body: String,
    var user: User,
    var urlPullRequest: String
)
