package matheusuehara.github.data.model


data class PullRequest(var id: Int,
                       var html_url: String,
                       var state: String,
                       var title: String,
                       var body: String,
                       var user: User)
