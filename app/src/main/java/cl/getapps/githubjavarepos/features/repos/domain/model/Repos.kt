package cl.getapps.githubjavarepos.features.repos.domain.model


data class Repos(
    val items: List<RepoModel>
)

data class RepoModel(
    val name: String?,
    val description: String?,
    val ownerModel: OwnerModel,
    val stargazers_count: Int,
    val forks: Int
)

data class OwnerModel(
    val login: String,
    val avatarUrl: String
)
