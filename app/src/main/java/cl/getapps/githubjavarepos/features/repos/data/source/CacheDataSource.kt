package cl.getapps.githubjavarepos.features.repos.data.source

import cl.getapps.githubjavarepos.core.data.CacheSource
import cl.getapps.githubjavarepos.features.repos.data.entity.ReposResponse
import cl.getapps.githubjavarepos.features.repos.data.remote.ReposParams
import io.reactivex.Single

interface CacheDataSource : CacheSource {
    fun getRepos(params: ReposParams): Single<ReposResponse>
}
