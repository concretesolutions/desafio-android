package cl.getapps.githubjavarepos.feature.repos.data.source

import cl.getapps.githubjavarepos.core.data.CacheSource
import cl.getapps.githubjavarepos.feature.repos.data.ReposResponse
import cl.getapps.githubjavarepos.feature.repos.data.remote.ReposParams
import io.reactivex.Flowable

interface CacheDataSource : CacheSource {
    fun getRepos(params: ReposParams): Flowable<ReposResponse>
}
