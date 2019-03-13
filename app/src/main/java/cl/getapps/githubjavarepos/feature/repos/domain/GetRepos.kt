package cl.getapps.githubjavarepos.feature.repos.domain

import cl.getapps.githubjavarepos.core.exception.Failure
import cl.getapps.githubjavarepos.core.functional.Either
import cl.getapps.githubjavarepos.core.interactor.UseCase
import cl.getapps.githubjavarepos.feature.repos.data.ReposResponse
import cl.getapps.githubjavarepos.feature.repos.data.remote.ReposParams
import cl.getapps.githubjavarepos.feature.repos.domain.repository.ReposRepository
import io.reactivex.Flowable


class GetRepos(private val reposRepository: ReposRepository) : UseCase<Flowable<ReposResponse>, ReposParams>() {
    override suspend fun run(params: ReposParams): Either<Failure, Flowable<ReposResponse>> {
        reposRepository.fetchRepos(params)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}