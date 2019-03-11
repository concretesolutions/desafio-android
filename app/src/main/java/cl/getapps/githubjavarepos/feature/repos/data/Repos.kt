package cl.getapps.githubjavarepos.feature.repos.data


data class Repos(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Repo>
) {
    fun toRepos() = items.mapTo(mutableListOf<cl.getapps.githubjavarepos.feature.repos.domain.Repo>(), createRepo)

    private val createRepo = {
            repo: cl.getapps.githubjavarepos.feature.repos.data.Repo ->
        cl.getapps.githubjavarepos.feature.repos.domain.Repo(
            repo.name,
            repo.description,
            repo.owner.toOwner(),
            repo.stargazers_count,
            repo.forks)
    }
}