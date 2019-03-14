package cl.getapps.githubjavarepos.feature.repos.domain

import cl.getapps.githubjavarepos.core.interactor.FlowableUseCase
import cl.getapps.githubjavarepos.core.interactor.executor.PostExecutionThread
import cl.getapps.githubjavarepos.core.interactor.executor.ThreadExecutor
import cl.getapps.githubjavarepos.feature.repos.data.ReposResponse
import cl.getapps.githubjavarepos.feature.repos.data.remote.ReposParams
import cl.getapps.githubjavarepos.feature.repos.domain.repository.ReposRepository
import io.reactivex.Flowable


class GetRepos(
    private val reposRepository: ReposRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : FlowableUseCase<ReposResponse, ReposParams>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: ReposParams): Flowable<ReposResponse> {
        return reposRepository.fetchRepos(params)
    }
}
