package cl.getapps.githubjavarepos.features.repos.domain.usecase

import cl.getapps.githubjavarepos.core.executor.PostExecutionThread
import cl.getapps.githubjavarepos.core.executor.ThreadExecutor
import cl.getapps.githubjavarepos.core.interactor.SingleUseCase
import cl.getapps.githubjavarepos.features.repos.data.entity.ReposResponse
import cl.getapps.githubjavarepos.features.repos.data.remote.ReposParams
import cl.getapps.githubjavarepos.features.repos.domain.repository.ReposRepository
import io.reactivex.Single


class GetRepos(
    private val reposRepository: ReposRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<ReposResponse, ReposParams>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: ReposParams): Single<ReposResponse> {
        return reposRepository.fetchRepos(params)
    }
}
