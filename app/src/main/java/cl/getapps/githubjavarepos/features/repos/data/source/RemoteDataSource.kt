package cl.getapps.githubjavarepos.features.repos.data.source


import cl.getapps.githubjavarepos.core.data.RemoteSource
import cl.getapps.githubjavarepos.features.repos.data.entity.ReposResponse
import cl.getapps.githubjavarepos.features.repos.data.remote.ReposParams
import io.reactivex.Single

interface RemoteDataSource : RemoteSource {
    fun fetchRepos(params: ReposParams): Single<ReposResponse>
}
