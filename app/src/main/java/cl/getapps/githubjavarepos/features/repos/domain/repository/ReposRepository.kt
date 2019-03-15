package cl.getapps.githubjavarepos.features.repos.domain.repository

import cl.getapps.githubjavarepos.features.repos.data.entity.ReposResponse
import cl.getapps.githubjavarepos.features.repos.data.remote.ReposParams
import io.reactivex.Single

interface ReposRepository {
    fun fetchRepos(params: ReposParams): Single<ReposResponse>
}
