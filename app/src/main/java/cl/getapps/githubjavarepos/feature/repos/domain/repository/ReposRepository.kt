package cl.getapps.githubjavarepos.feature.repos.domain.repository

import cl.getapps.githubjavarepos.feature.repos.data.ReposResponse
import cl.getapps.githubjavarepos.feature.repos.data.remote.ReposParams
import io.reactivex.Flowable

interface ReposRepository {
    fun fetchRepos(params: ReposParams): Flowable<ReposResponse>
}
