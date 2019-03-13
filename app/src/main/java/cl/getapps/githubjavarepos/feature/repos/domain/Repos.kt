package cl.getapps.githubjavarepos.feature.repos.domain


data class Repos(
    val items: List<Repo>
)

data class Repo(
    val name: String,
    val description: String,
    val owner: Owner,
    val stargazers_count: Int,
    val forks: Int
)

data class Owner(
    val login: String,
    val avatarUrl: String
)
