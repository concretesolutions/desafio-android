package cl.getapps.githubjavarepos.feature.repos.domain


data class Repo(
    val name: String,
    val description: String,
    val owner: Owner,
    val stargazers_count: Int,
    val forks: Int
)