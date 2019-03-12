package cl.getapps.githubjavarepos.feature.repos.data.remote


class ReposRemoteDataSource(private val reposAPI: ReposAPI) {

    fun fetchRepos(page: String) = reposAPI.fetchRepos(page)
}