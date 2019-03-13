package cl.getapps.githubjavarepos.feature.repos.data.source


import cl.getapps.githubjavarepos.core.data.RemoteSource
import cl.getapps.githubjavarepos.feature.repos.data.ReposResponse
import cl.getapps.githubjavarepos.feature.repos.data.remote.ReposParams
import io.reactivex.Flowable

interface RemoteDataSource : RemoteSource {
    fun fetchRepos(params: ReposParams): Flowable<ReposResponse>
}
