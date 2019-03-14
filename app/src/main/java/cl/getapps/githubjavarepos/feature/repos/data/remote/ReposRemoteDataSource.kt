package cl.getapps.githubjavarepos.feature.repos.data.remote

import cl.getapps.githubjavarepos.feature.repos.data.source.RemoteDataSource


class ReposRemoteDataSource(private val reposAPI: ReposAPI): RemoteDataSource {

    override fun fetchRepos(params: ReposParams) = reposAPI.fetchRepos(params.page)
}

data class ReposParams(val page: String = "1")
