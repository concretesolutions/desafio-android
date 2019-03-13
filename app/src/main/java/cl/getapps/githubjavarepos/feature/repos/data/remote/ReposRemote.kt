package cl.getapps.githubjavarepos.feature.repos.data.remote


class ReposRemoteDataSource(private val reposAPI: ReposAPI) {

    fun fetchRepos(repoParams: RepoParams) = reposAPI.fetchRepos(repoParams.page)
}

inline class ReposParams(val page: String)
