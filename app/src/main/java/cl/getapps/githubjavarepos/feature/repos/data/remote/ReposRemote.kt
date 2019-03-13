package cl.getapps.githubjavarepos.feature.repos.data.remote


class ReposRemoteDataSource(private val reposAPI: ReposAPI) {

    fun fetchRepos(repoParams: ReposParams) = reposAPI.fetchRepos(repoParams.page)
}

data class ReposParams(val page: String)
